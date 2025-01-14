package com.libreria.libreria.domain.usecase.libro;

import com.libreria.libreria.domain.model.libro.Libro;
import com.libreria.libreria.domain.model.libro.gateway.LibroGateway;
import com.libreria.libreria.domain.usecase.categoria.CategoriaUseCase;
import com.libreria.libreria.domain.usecase.editorial.EditorialUseCase;
import com.libreria.libreria.domain.usecase.escritor.EscritorUseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class LibroUseCase {

    private final LibroGateway libroGateway;
    private final EscritorUseCase escritorUseCase;
    private final CategoriaUseCase categoriaUseCase;
    private final EditorialUseCase editorialUseCase;

    public Mono<Libro> crearLibro(Libro libro){
        return Mono.zip(
                        escritorUseCase.consultarEscritor(libro.getIdEscritor()),
                        categoriaUseCase.consultarCategoria(libro.getIdCategoria()),
                        editorialUseCase.consultarEditorial(libro.getIdEditorial())
                )
                .flatMap(tuple -> libroGateway.crearLibro(libro));
    }
}
