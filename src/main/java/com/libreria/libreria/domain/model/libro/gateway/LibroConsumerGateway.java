package com.libreria.libreria.domain.model.libro.gateway;

import com.libreria.libreria.domain.model.libro.Caratula;
import reactor.core.publisher.Mono;

public interface LibroConsumerGateway {
    Mono<Caratula> consultarCaratula(Integer idLibro);
}
