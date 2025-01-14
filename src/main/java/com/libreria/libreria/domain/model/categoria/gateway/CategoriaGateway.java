package com.libreria.libreria.domain.model.categoria.gateway;

import com.libreria.libreria.domain.model.categoria.Categoria;
import reactor.core.publisher.Mono;

public interface CategoriaGateway {
    Mono<Categoria> crearCategoria(Categoria categoria);
    Mono<Categoria> consultarCategoria(Integer id);
}
