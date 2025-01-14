package com.libreria.libreria.domain.usecase.editorial;

import com.libreria.libreria.domain.model.editorial.Editorial;
import com.libreria.libreria.domain.model.editorial.gateway.EditorialGateway;
import com.libreria.libreria.domain.model.ex.BusinessException;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class EditorialUseCase {

    private final EditorialGateway gateway;

    public Mono<Editorial> crearEditorial(Editorial editorial){
        return gateway.crearEditorial(editorial);
    }

    public Mono<Editorial> consultarEditorial(Integer id){
        return gateway.consultarEditorial(id)
                .switchIfEmpty(Mono.error(new BusinessException(BusinessException.Type.EDITORIAL_NO_EXISTE)));
    }

}
