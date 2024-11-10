package com.libreria.libreria.domain.usecase.escritor;

import com.libreria.libreria.domain.model.escritor.Escritor;
import com.libreria.libreria.domain.model.escritor.gateway.EscritorGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class EscritorUseCase {

    private final EscritorGateway escritorGateway;

    public Mono<Escritor> crearEscritor(Escritor escritor){
        return escritorGateway.crearEscritor(escritor);
    }

}
