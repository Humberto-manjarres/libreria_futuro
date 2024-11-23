package com.libreria.libreria.domain.usecase.escritor;

import com.libreria.libreria.domain.model.escritor.Escritor;
import com.libreria.libreria.domain.model.escritor.gateway.EscritorGateway;
import com.libreria.libreria.domain.model.ex.BusinessException;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class EscritorUseCase {

    private final EscritorGateway escritorGateway;

    public Mono<Escritor> crearEscritor(Escritor escritor){
        return escritorGateway.crearEscritor(escritor);
    }

    public Mono<Escritor> consultarEscritor(String identificacion) {
        return escritorGateway.consultarEscritor(identificacion)
                .switchIfEmpty(Mono.error(new BusinessException(BusinessException.Type.ESCRITOR_NO_EXISTE)));
    }
}
