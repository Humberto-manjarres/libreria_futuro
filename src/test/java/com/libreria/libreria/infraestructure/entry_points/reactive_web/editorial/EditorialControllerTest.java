package com.libreria.libreria.infraestructure.entry_points.reactive_web.editorial;

import com.libreria.libreria.domain.model.editorial.Editorial;
import com.libreria.libreria.domain.usecase.editorial.EditorialUseCase;
import com.libreria.libreria.infraestructure.entry_points.reactive_web.editorial.dto.EditorialDTO;
import com.libreria.libreria.infraestructure.entry_points.reactive_web.editorial.transformers.EditorialTransformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

class EditorialControllerTest {

    @InjectMocks
    private EditorialController controller;

    @Mock
    private EditorialUseCase useCase;

    @Mock
    private EditorialTransformer transformer;

    @BeforeEach
    void beforeAll() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void crearEditorial(){
        Mockito.when(transformer.transformarAEditorial(getEditorialDTO())).thenReturn(getEditorial());
        Mockito.when(useCase.crearEditorial(getEditorial())).thenReturn(Mono.just(getEditorial()));
        Mockito.when(transformer.transformarAEditorialDTO(getEditorial())).thenReturn(getEditorialDTO());
        Mono<EditorialDTO> editorialDTO = controller.crearEditorial(getEditorialDTO());
        StepVerifier.create(editorialDTO)
                .expectNext(getEditorialDTO()).verifyComplete();
    }

    @Test
    void consultarEditorial(){
        Mockito.when(useCase.consultarEditorial(1)).thenReturn(Mono.just(getEditorial()));
        Mockito.when(transformer.transformarAEditorialDTO(getEditorial())).thenReturn(getEditorialDTO());
        Mono<EditorialDTO> editorialDTO = controller.consultarEditorial(1);
        StepVerifier.create(editorialDTO)
                .expectNext(getEditorialDTO()).verifyComplete();

    }

    private Editorial getEditorial(){
        return Editorial.builder().id(1).nombre("ABC").build();
    }

    private EditorialDTO getEditorialDTO(){
        return EditorialDTO.builder().id(1).nombre("ABC").build();
    }
}