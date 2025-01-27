package com.libreria.libreria.domain.model.libro.gateway;

import com.libreria.libreria.domain.model.libro.DetalleLibro;
import reactor.core.publisher.Mono;

public interface DetalleLibroGateway {
    Mono<DetalleLibro> agregarDetalleLibro(DetalleLibro detalleLibro);
}
