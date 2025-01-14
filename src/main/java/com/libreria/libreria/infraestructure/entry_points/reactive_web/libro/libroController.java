package com.libreria.libreria.infraestructure.entry_points.reactive_web.libro;

import com.libreria.libreria.domain.usecase.libro.LibroUseCase;
import com.libreria.libreria.infraestructure.entry_points.reactive_web.libro.dto.LibroDTO;
import com.libreria.libreria.infraestructure.entry_points.reactive_web.libro.transformers.LibroTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

}
