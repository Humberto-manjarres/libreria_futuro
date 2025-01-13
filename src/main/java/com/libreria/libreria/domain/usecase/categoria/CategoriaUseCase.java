package com.libreria.libreria.domain.usecase.categoria;

import com.libreria.libreria.domain.model.categoria.Categoria;
import com.libreria.libreria.domain.model.categoria.gateway.CategoriaGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CategoriaUseCase {

    private final CategoriaGateway gateway;

    public Mono<Categoria> craarCategoria(Categoria categoria){
        return gateway.crearCategoria(categoria);
    }
}
