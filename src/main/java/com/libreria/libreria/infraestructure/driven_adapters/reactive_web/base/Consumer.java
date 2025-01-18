package com.libreria.libreria.infraestructure.driven_adapters.reactive_web.base;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.libreria.libreria.domain.model.ex.BusinessException;
import io.netty.channel.ChannelOption;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

import java.time.Duration;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public abstract class Consumer {

    private Logger logger = LogManager.getLogger(this.getClass());

    @Setter
    private WebClient webClient;

    @Getter
    private ObjectMapper mapper;

    protected Consumer(String baseURL) {
        webClient = WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(
                        HttpClient.create(
                                        ConnectionProvider.builder("custom")
                                                .maxIdleTime(Duration.ofSeconds(120))
                                                .build()
                                ).responseTimeout(Duration.ofMillis(120000))
                                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 120000)))
                .baseUrl(baseURL)
                .codecs(codecs -> codecs
                        .defaultCodecs()
                        .maxInMemorySize(500 * 1024))
                .filter(logRequest())
                .filter(logResponseStatus())
                .build();
        mapper = new ObjectMapper();
    }

    /**
     * Petición con RequestParam o RequestHeader* */
    public <R> Mono<R> getRequest(String uri, Class<R> responseClass, Map<String, String> build) {
        return executeRequest(webClient.get().uri(buildUriWithParams(uri, build)), responseClass, Optional.empty());
    }

    /**
     * Petición con PathVariable* */
    public <R> Mono<R> getRequest(String uri, Class<R> responseClass) {
        return executeRequest(webClient.get().uri(uri), responseClass, Optional.empty());
    }


    /**
     * Petición con RequestParam o RequestHeader* */
    public <B, R> Flux<R> postFluxRequest(String uri, B body, Class<R> responseClass) {
        return executeFluxRequest(webClient.method(HttpMethod.POST)
                .uri(uri)
                .body(BodyInserters.fromPublisher(Mono.just(body), (Class) body.getClass())), responseClass, Optional.of(body));
    }


    private <R, B> Mono<R> executeRequest(WebClient.RequestHeadersSpec requestHeadersSpec, Class<R> responseClass, Optional<B> body) {
        return requestHeadersSpec
                .headers(httpHeaders -> configureHeaders((HttpHeaders) httpHeaders))
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, handleError(BusinessException.Type.SERVICE_CLIENT_ERROR, body))
                .onStatus(HttpStatusCode::is5xxServerError, handleError(BusinessException.Type.SERVICE_SERVER_ERROR, body))
                .bodyToMono(responseClass);
    }

    private <R, B> Flux<R> executeFluxRequest(WebClient.RequestHeadersSpec requestHeadersSpec, Class<R> responseClass, Optional<B> body) {
        return requestHeadersSpec
                .headers(httpHeaders -> configureHeaders((HttpHeaders) httpHeaders))
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, handleError(body))
                .onStatus(HttpStatusCode::is5xxServerError, handleError(body))
                .bodyToFlux(responseClass);
    }

    /**
     * @param httpHeaders
     */
    public abstract void configureHeaders(HttpHeaders httpHeaders);


    private String buildUriWithParams(String uri, Map<String, String> uriVariables) {
        return UriComponentsBuilder.newInstance()
                .path(uri)
                .buildAndExpand(uriVariables)
                .toUriString();
    }


    private ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            logger.info("Sending: {} {}", clientRequest.method(), clientRequest.url());
            clientRequest.headers()
                    .forEach((name, values) -> values.forEach(value -> logger.info("{}={}", name, value)));
            return Mono.just(clientRequest);
        });
    }


    private ExchangeFilterFunction logResponseStatus() {
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            logger.info("Received Status {}", clientResponse.statusCode());
            return Mono.just(clientResponse);
        });
    }

    private <B> Function<ClientResponse, Mono<? extends Throwable>> handleError(BusinessException.Type errorCode, Optional<B> body) {
        return response -> response.bodyToMono(String.class).flatMap(error -> {
            StringBuilder builder = new StringBuilder();
            if (body.isPresent()) {
                try {
                    builder.append("request: ").append(mapper.writeValueAsString(body.get())).append(". -> ");
                } catch (JsonProcessingException e) {
                    logger.error("Error al transformar request a JSON String", e);
                }
            }
            builder.append("response: ").append(error);
            logger.error(builder.toString());
            return Mono.error(new BusinessException(errorCode));
        });
    }


    private <B> Function<ClientResponse, Mono<? extends Throwable>> handleError(Optional<B> body) {
        return response -> response.bodyToMono(RuntimeException.class)
                .flatMap(error -> {
                            StringBuilder builder = new StringBuilder();
                            if (body.isPresent()) {
                                try {
                                    builder.append("request: ").append(mapper.writeValueAsString(body.get())).append(". -> ");
                                } catch (JsonProcessingException e) {
                                    logger.error("Error al transformar request a JSON String", e);
                                }
                            }
                    return Mono.error(new RuntimeException(response.statusCode().toString()));
                        }
                );
    }


}
