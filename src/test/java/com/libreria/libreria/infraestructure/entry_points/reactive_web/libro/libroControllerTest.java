package com.libreria.libreria.infraestructure.entry_points.reactive_web.libro;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
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

    @Test
    void consultarLibro_DeberiaRetornarJsonNode() {
        Integer idLibro = 1;
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode libroMock = objectMapper.createObjectNode();
        libroMock.put("id", idLibro);
        libroMock.put("nombre", "El Señor de los Anillos");

        Mockito.when(useCase.consultarLibro(idLibro)).thenReturn(Mono.just(libroMock));

        Mono<JsonNode> result = controller.consultarLibro(idLibro);

        StepVerifier.create(result)
                .expectNextMatches(jsonNode ->
                        jsonNode.get("id").asInt() == idLibro &&
                                "El Señor de los Anillos".equals(jsonNode.get("nombre").asText())
                )
                .verifyComplete();
    }


    private Libro getLibro(){
        return Libro.builder().nombre("ABC").build();
    }

    private LibroDTO getLibroDTO(){
        return LibroDTO.builder().nombre("ABC").build();
    }

}