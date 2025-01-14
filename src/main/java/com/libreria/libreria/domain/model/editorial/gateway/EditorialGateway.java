package com.libreria.libreria.domain.model.editorial.gateway;

import com.libreria.libreria.domain.model.editorial.Editorial;
import reactor.core.publisher.Mono;

public interface EditorialGateway {
    Mono<Editorial> crearEditorial(Editorial editorial);
}
