package com.libreria.libreria.infraestructure.entry_points.reactive_web.escritor;

import com.libreria.libreria.domain.model.escritor.Escritor;
import com.libreria.libreria.domain.usecase.escritor.EscritorUseCase;
import com.libreria.libreria.infraestructure.entry_points.reactive_web.escritor.dto.EscritorDTO;
import com.libreria.libreria.infraestructure.entry_points.reactive_web.escritor.transformers.EscritorTransformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

class EscritorControllerTest {

    @InjectMocks
    private EscritorController controller;

    @Mock
    private EscritorUseCase useCase;

    @Mock
    private EscritorTransformer transformer;

    @BeforeEach
    void beforeAll() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void crearEscritor(){
        Mockito.when(transformer.transformarAEscrito(escritorDTO())).thenReturn(escritor());
        Mockito.when(useCase.crearEscritor(escritor())).thenReturn(Mono.just(escritor()));
        Mockito.when(transformer.transformarAEscritoDTO(escritor())).thenReturn(escritorDTO());

        Mono<EscritorDTO> escritorDTOMono = controller.crearEscritor(escritorDTO());

        StepVerifier.create(escritorDTOMono)
                .expectNext(escritorDTO())
                .verifyComplete();

    }


    @Test
    void consultarEscritor(){
       Mockito.when(useCase.consultarEscritor(Mockito.anyString())).thenReturn(Mono.just(escritor()));
       Mockito.when(transformer.transformarAEscritoDTO(escritor())).thenReturn(escritorDTO());

        Mono<EscritorDTO> escritorDTOMono = controller.consultarEscritor("123");
        StepVerifier.create(escritorDTOMono)
                .expectNext(escritorDTO())
                .verifyComplete();
    }

    private EscritorDTO escritorDTO(){
        return EscritorDTO.builder().identificacion("123").nombre("ABC").build();
    }

    private Escritor escritor(){
        return Escritor.builder().identificacion("123").nombre("ABC").build();
    }

}