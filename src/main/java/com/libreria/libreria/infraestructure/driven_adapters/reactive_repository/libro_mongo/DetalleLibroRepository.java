package com.libreria.libreria.infraestructure.driven_adapters.reactive_repository.libro_mongo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface DetalleLibroRepository extends ReactiveMongoRepository<DetalleLibroEntity, String> {
}
