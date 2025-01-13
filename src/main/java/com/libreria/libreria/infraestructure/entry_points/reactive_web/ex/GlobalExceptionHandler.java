package com.libreria.libreria.infraestructure.entry_points.reactive_web.ex;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.libreria.libreria.domain.model.ex.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Component
public class GlobalExceptionHandler implements ErrorWebExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        log.error("Error en la solicitud: {}", ex.getMessage(), ex);

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String message = "Error inesperado";

        if (ex instanceof BusinessException) {
            status = HttpStatus.BAD_REQUEST;
            message = ex.getMessage();
        }

        if (ex instanceof DuplicateKeyException){
            status = HttpStatus.CONFLICT;
            message = "La identificación ya existe en el sistema, ingrese otra";
        }

        // Configurar la respuesta HTTP
        exchange.getResponse().setStatusCode(status);
        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);

        // Crear el cuerpo de la respuesta como instancia de ApiError
        ApiError errorResponse = ApiError.builder()
                .backenMessage(ex.getLocalizedMessage())
                .url(exchange.getRequest().getURI().toString())
                .method(exchange.getRequest().getMethod().name())
                .timeStamp(LocalDateTime.now())
                .message(message)
                .build();

        // Serializar el cuerpo de respuesta a JSON
        byte[] errorBytes = serializeToJson(errorResponse);

        // Escribir el cuerpo JSON en la respuesta
        return exchange.getResponse()
                .writeWith(Mono.just(exchange.getResponse().bufferFactory().wrap(errorBytes)));
    }

    private byte[] serializeToJson(Object object) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.findAndRegisterModules(); // Registra automáticamente módulos como JSR310
            return objectMapper.writeValueAsBytes(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{\"message\":\"Error serializing ApiError\"}".getBytes();
        }
    }


}
