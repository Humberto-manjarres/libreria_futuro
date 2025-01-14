package com.libreria.libreria.infraestructure.driven_adapters.r2dbc_repository.editorial;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface EditorialRepository extends ReactiveCrudRepository<EditorialEntity,Integer> {
}
