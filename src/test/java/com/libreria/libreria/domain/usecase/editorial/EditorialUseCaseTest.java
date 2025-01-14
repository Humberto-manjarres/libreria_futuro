package com.libreria.libreria.domain.usecase.editorial;

import com.libreria.libreria.domain.model.editorial.Editorial;
import com.libreria.libreria.domain.model.editorial.gateway.EditorialGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

class EditorialUseCaseTest {

    @InjectMocks
    private EditorialUseCase useCase;

    @Mock
    private EditorialGateway gateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void crearEditorial(){
        Mockito.when(gateway.crearEditorial(getEditorial())).thenReturn(Mono.just(getEditorial()));
        Mono<Editorial> editorial = useCase.crearEditorial(getEditorial());
        StepVerifier.create(editorial)
                .expectNextMatches(editorial1 -> editorial1.getNombre().equals(getEditorial().getNombre()));
    }

    private Editorial getEditorial(){
        return Editorial.builder().nombre("ABC").build();
    }

}