package com.libreria.libreria.infraestructure.entry_points.reactive_web.escritor;

import com.libreria.libreria.domain.usecase.escritor.EscritorUseCase;
import com.libreria.libreria.infraestructure.entry_points.reactive_web.escritor.dto.EscritorDTO;
import com.libreria.libreria.infraestructure.entry_points.reactive_web.escritor.transformers.EscritorTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

}
