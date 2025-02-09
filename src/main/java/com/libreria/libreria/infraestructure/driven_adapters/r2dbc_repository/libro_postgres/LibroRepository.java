package com.libreria.libreria.infraestructure.driven_adapters.r2dbc_repository.libro_postgres;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface LibroRepository extends ReactiveCrudRepository<LibroEntity,Integer> {
}
