package com.libreria.libreria.infraestructure.driven_adapters.r2dbc_repository.editorial;

import com.libreria.libreria.domain.model.editorial.Editorial;
import com.libreria.libreria.domain.model.editorial.gateway.EditorialGateway;
import com.libreria.libreria.infraestructure.driven_adapters.r2dbc_repository.editorial.transformer.EditorialR2dbcTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;


@Repository
@RequiredArgsConstructor
public class EditorialAdapter implements EditorialGateway {

    private final EditorialRepository repository;

    private final EditorialR2dbcTransformer transformer;

    @Override
    public Mono<Editorial> crearEditorial(Editorial editorial) {
        return repository.save(transformer.transformarAEditorialEntity(editorial))
                .map(transformer::transformarAEditorial);
    }

    @Override
    public Mono<Editorial> consultarEditorial(Integer id) {
        return repository.findById(id).map(transformer::transformarAEditorial);
    }

}
