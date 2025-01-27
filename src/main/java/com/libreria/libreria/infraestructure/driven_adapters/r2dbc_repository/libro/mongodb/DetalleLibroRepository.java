package com.libreria.libreria.infraestructure.driven_adapters.r2dbc_repository.libro.mongodb;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface DetalleLibroRepository extends ReactiveMongoRepository<DetalleLibroEntity, String> {
}
