package com.libreria.libreria.infraestructure.driven_adapters.r2dbc_repository.escritor;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface EscritorRepository extends ReactiveCrudRepository<EscritorEntity,Integer> {
}
