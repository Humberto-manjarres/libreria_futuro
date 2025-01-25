package com.libreria.libreria.infraestructure.entry_points.reactive_web.prueba;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/prueba")
public class PruebaController {

    @GetMapping
    public Mono<String> prueba(){
        return Mono.just("Prueba Dockerfile");
    }

}
