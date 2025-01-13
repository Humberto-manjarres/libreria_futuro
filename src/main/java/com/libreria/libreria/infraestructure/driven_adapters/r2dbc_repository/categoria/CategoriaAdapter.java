package com.libreria.libreria.infraestructure.driven_adapters.r2dbc_repository.categoria;

import com.libreria.libreria.domain.model.categoria.Categoria;
import com.libreria.libreria.domain.model.categoria.gateway.CategoriaGateway;
import com.libreria.libreria.infraestructure.driven_adapters.r2dbc_repository.categoria.trasnformer.CategoriaR2dbcTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class CategoriaAdapter implements CategoriaGateway {

    private final CategoriaRepository repository;

    private final CategoriaR2dbcTransformer transformer;

    @Override
    public Mono<Categoria> crearCategoria(Categoria categoria) {
        return repository.save(transformer.transformarACategoriaEntity(categoria))
                .map(transformer::transformarACategoria);
    }

}
