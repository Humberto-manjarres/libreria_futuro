package com.libreria.libreria.domain.usecase.libro;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.libreria.libreria.domain.model.ex.BusinessException;
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
    private final ObjectMapper objectMapper = new ObjectMapper();

    public Mono<Libro> crearLibro(Libro libro){
        return Mono.zip(
                        escritorUseCase.consultarEscritor(libro.getIdEscritor()),
                        categoriaUseCase.consultarCategoria(libro.getIdCategoria()),
                        editorialUseCase.consultarEditorial(libro.getIdEditorial())
                )
                .flatMap(tuple -> libroGateway.crearLibro(libro));
    }

    public Mono<JsonNode> consultarLibro(Integer id){
        return libroGateway.consultarLibro(id)
                .switchIfEmpty(Mono.error(new BusinessException(BusinessException.Type.LIBRO_NO_EXISTE)))
                .flatMap(libro -> Mono.zip(
                        escritorUseCase.consultarEscritor(libro.getIdEscritor()),
                        categoriaUseCase.consultarCategoria(libro.getIdCategoria()),
                        editorialUseCase.consultarEditorial(libro.getIdEditorial())
                ).map(tuple -> {
                    ObjectNode jsonNode = objectMapper.createObjectNode();
                    jsonNode.put("id", libro.getId());
                    jsonNode.put("nombre", libro.getNombre());
                    jsonNode.put("descripcion", libro.getDescripcion());

                    jsonNode.set("escritor", objectMapper.valueToTree(tuple.getT1()));
                    jsonNode.set("categoria", objectMapper.valueToTree(tuple.getT2()));
                    jsonNode.set("editorial", objectMapper.valueToTree(tuple.getT3()));

                    return jsonNode;
                }));
    }

}
