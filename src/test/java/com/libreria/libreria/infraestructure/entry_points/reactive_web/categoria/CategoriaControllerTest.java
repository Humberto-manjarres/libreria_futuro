package com.libreria.libreria.infraestructure.entry_points.reactive_web.categoria;

import com.libreria.libreria.domain.model.categoria.Categoria;
import com.libreria.libreria.domain.usecase.categoria.CategoriaUseCase;
import com.libreria.libreria.infraestructure.entry_points.reactive_web.categoria.dto.CategoriaDTO;
import com.libreria.libreria.infraestructure.entry_points.reactive_web.categoria.transformers.CategoriaTransformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

class CategoriaControllerTest {

    @InjectMocks
    private CategoriaController controller;

    @Mock
    private CategoriaUseCase useCase;

    @Mock
    private CategoriaTransformer transformer;

    @BeforeEach
    void beforeAll() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void crearCategoria(){
        Mockito.when(transformer.transformarACategoria(getCategoriaDTO())).thenReturn(getCategoria());
        Mockito.when(useCase.craarCategoria(getCategoria())).thenReturn(Mono.just(getCategoria()));
        Mockito.when(transformer.transformarACategoriaDTO(getCategoria())).thenReturn(getCategoriaDTO());

        Mono<CategoriaDTO> categoria = controller.crearCategoria(getCategoriaDTO());
        StepVerifier.create(categoria)
                .expectNext(getCategoriaDTO()).verifyComplete();
    }

    private CategoriaDTO getCategoriaDTO(){
        return CategoriaDTO.builder().nombre("ABC").build();
    }

    private Categoria getCategoria(){
        return Categoria.builder().nombre("ABC").build();
    }
}