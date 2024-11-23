package com.libreria.libreria.infraestructure.entry_points.reactive_web.escritor;

import com.libreria.libreria.domain.usecase.escritor.EscritorUseCase;
import com.libreria.libreria.infraestructure.entry_points.reactive_web.escritor.dto.EscritorDTO;
import com.libreria.libreria.infraestructure.entry_points.reactive_web.escritor.transformers.EscritorTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/escritor")
public class EscritorController {

    private final EscritorUseCase escritorUseCase;

    private final EscritorTransformer transformer;

    @PostMapping(path = "/crear")
    public Mono<EscritorDTO> crearEscritor(@RequestBody EscritorDTO escritorDTO){
        return escritorUseCase.crearEscritor(transformer.transformarAEscrito(escritorDTO))
                .map(transformer::transformarAEscritoDTO);
    }

    @GetMapping(path = "/consultar/{identificacion}")
    public Mono<EscritorDTO> consultarEscritor(@PathVariable String identificacion){
        return escritorUseCase.consultarEscritor(identificacion)
                .map(transformer::transformarAEscritoDTO);
    }

}
