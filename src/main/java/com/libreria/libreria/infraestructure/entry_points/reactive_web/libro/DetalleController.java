package com.libreria.libreria.infraestructure.entry_points.reactive_web.libro;

import com.libreria.libreria.domain.usecase.libro.LibroUseCase;
import com.libreria.libreria.infraestructure.entry_points.reactive_web.libro.dto.DetalleLibroDTO;
import com.libreria.libreria.infraestructure.entry_points.reactive_web.libro.transformers.LibroTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/detalle-libro")
public class DetalleController {

    private final LibroUseCase libroUseCase;

    private final LibroTransformer transformer;

    @PostMapping(path = "/agregar")
    public Mono<DetalleLibroDTO> detalleLibro(@RequestBody DetalleLibroDTO detalleLibroDTO){
        return libroUseCase.agregarDetalleLibro(transformer.transformarADetalleLibro(detalleLibroDTO))
                .map(transformer::transformarADetalleLibroDTO);
    }


}
