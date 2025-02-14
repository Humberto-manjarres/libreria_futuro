package com.libreria.libreria.infraestructure.entry_points.reactive_web.libro;

import com.libreria.libreria.domain.model.libro.DetalleLibro;
import com.libreria.libreria.domain.usecase.libro.LibroUseCase;
import com.libreria.libreria.infraestructure.entry_points.reactive_web.libro.dto.DetalleLibroDTO;
import com.libreria.libreria.infraestructure.entry_points.reactive_web.libro.transformers.LibroTransformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class DetalleControllerTest {

    @InjectMocks
    private DetalleController detalleController;

    @Mock
    private LibroUseCase libroUseCase;

    @Mock
    private LibroTransformer transformer;

    @BeforeEach
    void beforeAll() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void detalleLibro(){
        Mockito.when(transformer.transformarADetalleLibro(getDetalleLibroDTO())).thenReturn(getDetalleLibro());
        Mockito.when(libroUseCase.agregarDetalleLibro(getDetalleLibro())).thenReturn(Mono.just(getDetalleLibro()));
        Mockito.when(transformer.transformarADetalleLibroDTO(getDetalleLibro())).thenReturn(getDetalleLibroDTO());
        Mono<DetalleLibroDTO> response = detalleController.detalleLibro(getDetalleLibroDTO());
        StepVerifier.create(response).expectNext(getDetalleLibroDTO()).verifyComplete();
    }

    private DetalleLibroDTO getDetalleLibroDTO(){
        return DetalleLibroDTO.builder().nombre("ABC").build();
    }

    private DetalleLibro getDetalleLibro(){
        return DetalleLibro.builder().nombre("ABC").build();
    }


}