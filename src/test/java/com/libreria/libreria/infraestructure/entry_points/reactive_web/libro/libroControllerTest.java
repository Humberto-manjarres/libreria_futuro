package com.libreria.libreria.infraestructure.entry_points.reactive_web.libro;

import com.libreria.libreria.domain.model.libro.Libro;
import com.libreria.libreria.domain.usecase.libro.LibroUseCase;
import com.libreria.libreria.infraestructure.entry_points.reactive_web.libro.dto.LibroDTO;
import com.libreria.libreria.infraestructure.entry_points.reactive_web.libro.transformers.LibroTransformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

class libroControllerTest {

    @InjectMocks
    private libroController controller;

    @Mock
    private LibroUseCase useCase;

    @Mock
    private LibroTransformer transformer;

    @BeforeEach
    void beforeAll() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void crearLibro(){
        Mockito.when(transformer.transformarALibro(getLibroDTO())).thenReturn(getLibro());
        Mockito.when(useCase.crearLibro(getLibro())).thenReturn(Mono.just(getLibro()));
        Mockito.when(transformer.transformarALibroDTO(getLibro())).thenReturn(getLibroDTO());
        Mono<LibroDTO> libroDTO = controller.crearLibro(getLibroDTO());
        StepVerifier.create(libroDTO).expectNext(getLibroDTO()).verifyComplete();

    }

    private Libro getLibro(){
        return Libro.builder().nombre("ABC").build();
    }

    private LibroDTO getLibroDTO(){
        return LibroDTO.builder().nombre("ABC").build();
    }

}