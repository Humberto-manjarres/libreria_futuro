package com.libreria.libreria.domain.model.libro.gateway;

import reactor.core.publisher.Mono;

public interface LibroQueueGateway {

    Mono<Void> libroCreado(Object object);
}
