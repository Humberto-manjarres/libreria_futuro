package com.libreria.libreria.domain.usecase.categoria;

import com.libreria.libreria.domain.model.categoria.Categoria;
import com.libreria.libreria.domain.model.categoria.gateway.CategoriaGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

class CategoriaUseCaseTest {

    @InjectMocks
    private CategoriaUseCase useCase;

    @Mock
    private CategoriaGateway gateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void crearCategoria(){
        Mockito.when(gateway.crearCategoria(getCategoria())).thenReturn(Mono.just(getCategoria()));
        Mono<Categoria> categoria = useCase.craarCategoria(this.getCategoria());
        StepVerifier.create(categoria).expectNextMatches(categoria1 -> categoria1.getNombre().equals("ABC"));
    }

    private Categoria getCategoria(){
        return Categoria.builder().nombre("ABC").build();
    }

}