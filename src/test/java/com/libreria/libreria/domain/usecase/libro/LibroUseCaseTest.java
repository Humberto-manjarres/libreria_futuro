package com.libreria.libreria.domain.usecase.libro;

import com.fasterxml.jackson.databind.JsonNode;
import com.libreria.libreria.domain.model.categoria.Categoria;
import com.libreria.libreria.domain.model.editorial.Editorial;
import com.libreria.libreria.domain.model.escritor.Escritor;
import com.libreria.libreria.domain.model.libro.Caratula;
import com.libreria.libreria.domain.model.libro.Libro;
import com.libreria.libreria.domain.model.libro.gateway.LibroConsumerGateway;
import com.libreria.libreria.domain.model.libro.gateway.LibroGateway;
import com.libreria.libreria.domain.usecase.categoria.CategoriaUseCase;
import com.libreria.libreria.domain.usecase.editorial.EditorialUseCase;
import com.libreria.libreria.domain.usecase.escritor.EscritorUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

class LibroUseCaseTest {

    @InjectMocks
    private LibroUseCase libroUseCase;

    @Mock
    private LibroGateway gateway;

    @Mock
    private LibroConsumerGateway consumerGateway;

    @Mock
    private CategoriaUseCase categoriaUseCase;

    @Mock
    private EditorialUseCase editorialUseCase;

    @Mock
    private EscritorUseCase escritorUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void crearLibro(){
        Mockito.when(escritorUseCase.consultarEscritor("123")).thenReturn(Mono.just(getEscritor()));
        Mockito.when(categoriaUseCase.consultarCategoria(1)).thenReturn(Mono.just(getCategoria()));
        Mockito.when(editorialUseCase.consultarEditorial(1)).thenReturn(Mono.just(getEditorial()));
        Mockito.when(gateway.crearLibro(getLibro())).thenReturn(Mono.just(getLibro()));
        Mono<Libro> libro = libroUseCase.crearLibro(getLibro());
        StepVerifier.create(libro)
                .expectNextMatches(libro1 -> libro1.getNombre().equals("ABC")).verifyComplete();
    }

    @Test
    void consultarLibro(){
        Mockito.when(gateway.consultarLibro(1)).thenReturn(Mono.just(getLibro()));
        Mockito.when(escritorUseCase.consultarEscritor("123")).thenReturn(Mono.just(getEscritor()));
        Mockito.when(categoriaUseCase.consultarCategoria(1)).thenReturn(Mono.just(getCategoria()));
        Mockito.when(editorialUseCase.consultarEditorial(1)).thenReturn(Mono.just(getEditorial()));
        Mono<JsonNode> response = libroUseCase.consultarLibro(1);
        StepVerifier.create(response)
                .expectNextMatches(jsonNode -> jsonNode.get("id").asInt() == 1).verifyComplete();
    }

    @Test
    void consultarCaratulaLibro(){
        Mockito.when(consumerGateway.consultarCaratula(1)).thenReturn(Mono.just(getCaratula()));
        StepVerifier.create(libroUseCase.consultarCaratulaLibro(1))
                .expectNextMatches(caratula -> caratula.getCaratula().equals("ABC"));
    }

    private Escritor getEscritor(){
        return Escritor.builder().identificacion("123").build();
    }

    private Categoria getCategoria(){
        return Categoria.builder().id(1).build();
    }

    private Editorial getEditorial(){
        return Editorial.builder().id(1).build();
    }

    private Libro getLibro(){
        return Libro.builder().id(1).idEscritor("123").idCategoria(1).idEditorial(1).nombre("ABC").build();
    }

    private Caratula getCaratula(){
        return Caratula.builder().caratula("ABC").build();
    }

}