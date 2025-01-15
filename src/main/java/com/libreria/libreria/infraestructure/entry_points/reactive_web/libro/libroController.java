package com.libreria.libreria.infraestructure.entry_points.reactive_web.libro;

import com.fasterxml.jackson.databind.JsonNode;
import com.libreria.libreria.domain.usecase.libro.LibroUseCase;
import com.libreria.libreria.infraestructure.entry_points.reactive_web.editorial.dto.EditorialDTO;
import com.libreria.libreria.infraestructure.entry_points.reactive_web.libro.dto.LibroDTO;
import com.libreria.libreria.infraestructure.entry_points.reactive_web.libro.transformers.LibroTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/libro")
public class libroController {

    private final LibroUseCase libroUseCase;

    private final LibroTransformer transformer;

    @PostMapping(path = "/crear")
    public Mono<LibroDTO> crearLibro(@RequestBody LibroDTO libroDTO){
        return libroUseCase.crearLibro(transformer.transformarALibro(libroDTO))
                .map(transformer::transformarALibroDTO);
    }

    @GetMapping(path = "/consultar/{id}")
    public Mono<JsonNode> consultarLibro(@PathVariable Integer id){
        return libroUseCase.consultarLibro(id);
    }

}
