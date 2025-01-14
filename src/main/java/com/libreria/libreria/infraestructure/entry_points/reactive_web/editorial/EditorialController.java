package com.libreria.libreria.infraestructure.entry_points.reactive_web.editorial;

import com.libreria.libreria.domain.usecase.editorial.EditorialUseCase;
import com.libreria.libreria.infraestructure.entry_points.reactive_web.categoria.dto.CategoriaDTO;
import com.libreria.libreria.infraestructure.entry_points.reactive_web.editorial.dto.EditorialDTO;
import com.libreria.libreria.infraestructure.entry_points.reactive_web.editorial.transformers.EditorialTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/editorial")
public class EditorialController {

    private final EditorialUseCase editorialUseCase;

    private final EditorialTransformer transformer;

    @PostMapping(path = "/crear")
    public Mono<EditorialDTO> crearEditorial(@RequestBody EditorialDTO editorialDTO){
        return editorialUseCase.crearEditorial(transformer.transformarAEditorial(editorialDTO))
                .map(transformer::transformarAEditorialDTO);
    }

    @GetMapping(path = "/consultar/{id}")
    public Mono<EditorialDTO> consultarEditorial(@PathVariable Integer id){
        return editorialUseCase.consultarEditorial(id)
                .map(transformer::transformarAEditorialDTO);
    }

}
