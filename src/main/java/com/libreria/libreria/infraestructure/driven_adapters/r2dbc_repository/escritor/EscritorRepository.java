package com.libreria.libreria.infraestructure.driven_adapters.r2dbc_repository.escritor;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface EscritorRepository extends ReactiveCrudRepository<EscritorEntity,Integer> {
    Mono<EscritorEntity> findByIdentificacion(String identificacion);
}
