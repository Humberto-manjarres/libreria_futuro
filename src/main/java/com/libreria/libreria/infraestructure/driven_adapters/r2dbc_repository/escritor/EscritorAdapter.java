package com.libreria.libreria.infraestructure.driven_adapters.r2dbc_repository.escritor;

import com.libreria.libreria.domain.model.escritor.Escritor;
import com.libreria.libreria.domain.model.escritor.gateway.EscritorGateway;
import com.libreria.libreria.infraestructure.driven_adapters.r2dbc_repository.escritor.transformer.EscritorR2dbcTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class EscritorAdapter implements EscritorGateway {

    private final EscritorRepository repository;

    private final EscritorR2dbcTransformer r2dbcTransformer;

    @Override
    public Mono<Escritor> crearEscritor(Escritor escritor) {
        return repository.save(r2dbcTransformer.transformarAEscritorEntity(escritor))
                .map(r2dbcTransformer::transformarAEscritor);
    }

    @Override
    public Mono<Escritor> consultarEscritor(String identificacion) {
        return repository.findByIdentificacion(identificacion)
                .map(r2dbcTransformer::transformarAEscritor);
    }
}
