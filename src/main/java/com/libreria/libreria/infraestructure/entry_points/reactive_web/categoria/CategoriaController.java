package com.libreria.libreria.infraestructure.entry_points.reactive_web.categoria;

import com.libreria.libreria.domain.usecase.categoria.CategoriaUseCase;
import com.libreria.libreria.infraestructure.entry_points.reactive_web.categoria.dto.CategoriaDTO;
import com.libreria.libreria.infraestructure.entry_points.reactive_web.categoria.transformers.CategoriaTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/categoria")
public class CategoriaController {

    private final CategoriaUseCase categoriaUseCase;

    private final CategoriaTransformer transformer;

    @PostMapping(path = "/crear")
    public Mono<CategoriaDTO> crearCategoria(@RequestBody CategoriaDTO categoriaDTO){
        return categoriaUseCase.craarCategoria(transformer.transformarACategoria(categoriaDTO))
                .map(transformer::transformarACategoriaDTO);
    }

}
