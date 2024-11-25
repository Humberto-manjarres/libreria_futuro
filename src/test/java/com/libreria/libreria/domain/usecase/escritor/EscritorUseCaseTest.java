package com.libreria.libreria.domain.usecase.escritor;

import com.libreria.libreria.domain.model.escritor.Escritor;
import com.libreria.libreria.domain.model.escritor.gateway.EscritorGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class EscritorUseCaseTest {

    @InjectMocks
    private EscritorUseCase useCase;

    @Mock
    private EscritorGateway gateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void crearEscritor(){
        Mockito.when(gateway.crearEscritor(escritor()))
                .thenReturn(Mono.just(Escritor.builder().id(1).identificacion("123").nombre("ABC").build()));
        Mono<Escritor> escritorMono = useCase.crearEscritor(Mockito.mock(Escritor.class));
        StepVerifier.create(escritorMono)
                .expectNextMatches(escritor -> escritor.getId() == 1);

    }

    @Test
    void consultarEscritor(){
        Mockito.when(gateway.consultarEscritor(Mockito.anyString()))
                .thenReturn(Mono.just(Escritor.builder().id(1).identificacion("123").nombre("ABC").build()));

        Mono<Escritor> escritorMono = useCase.consultarEscritor("123");
        StepVerifier.create(escritorMono)
                .expectNextMatches(escritor -> escritor.getIdentificacion().equals("123"));

    }

    private Escritor escritor(){
        return Escritor.builder().identificacion("123").nombre("ABC").build();
    }
}