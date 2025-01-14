package com.libreria.libreria.domain.model.libro.gateway;

import com.libreria.libreria.domain.model.libro.Libro;
import reactor.core.publisher.Mono;

public interface LibroGateway {
    Mono<Libro> crearLibro(Libro libro);
}
