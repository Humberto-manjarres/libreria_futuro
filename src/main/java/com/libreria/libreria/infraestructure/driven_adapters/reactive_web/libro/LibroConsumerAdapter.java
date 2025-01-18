package com.libreria.libreria.infraestructure.driven_adapters.reactive_web.libro;

import com.libreria.libreria.domain.model.libro.Caratula;
import com.libreria.libreria.domain.model.libro.gateway.LibroConsumerGateway;
import com.libreria.libreria.infraestructure.driven_adapters.reactive_web.base.Consumer;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Setter
@Service
public class LibroConsumerAdapter extends Consumer implements LibroConsumerGateway {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Setter
    @Value("${caratula.uri}")
    private String uri;

    protected LibroConsumerAdapter(@Value("${api.caratula.url}") String baseURL) {
        super(baseURL);
    }

    @Override
    public Mono<Caratula> consultarCaratula(Integer idLibro) {
        return getRequest(uri.concat(idLibro.toString()), Caratula.class).onErrorResume (
                throwable -> {
                    logger.error(String.format("Error al consultar caratula del libro", idLibro), throwable);
                    return Mono.just(Caratula.builder().build());
                }
        );
    }

    @Override
    public void configureHeaders(HttpHeaders httpHeaders) {
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    }
}
