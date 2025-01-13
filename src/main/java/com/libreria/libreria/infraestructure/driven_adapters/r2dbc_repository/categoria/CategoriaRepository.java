package com.libreria.libreria.infraestructure.driven_adapters.r2dbc_repository.categoria;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CategoriaRepository extends ReactiveCrudRepository<CategoriaEntity,Integer> {
}
