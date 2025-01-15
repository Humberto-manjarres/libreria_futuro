package com.libreria.libreria.infraestructure.driven_adapters.r2dbc_repository.libro;

import com.libreria.libreria.domain.model.libro.Libro;
import com.libreria.libreria.domain.model.libro.gateway.LibroGateway;
import com.libreria.libreria.infraestructure.driven_adapters.r2dbc_repository.libro.transformer.LibroR2dbcTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class LibroAdapter implements LibroGateway {

    private final LibroRepository repository;

    private final LibroR2dbcTransformer transformer;

    @Override
    public Mono<Libro> crearLibro(Libro libro) {
        return repository.save(transformer.transformarAEditorialEntity(libro)).map(transformer::transformarALibro);
    }

    @Override
    public Mono<Libro> consultarLibro(Integer id) {
        return repository.findById(id).map(transformer::transformarALibro);
    }

}
