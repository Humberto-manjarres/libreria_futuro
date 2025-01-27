package com.libreria.libreria.infraestructure.driven_adapters.r2dbc_repository.libro.mongodb;


import com.libreria.libreria.domain.model.libro.DetalleLibro;
import com.libreria.libreria.domain.model.libro.gateway.DetalleLibroGateway;
import com.libreria.libreria.infraestructure.driven_adapters.r2dbc_repository.libro.mongodb.transformer.DetalleLibroTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class DetalleLibroAdapter implements DetalleLibroGateway {

    private final DetalleLibroRepository repository;

    private final DetalleLibroTransformer transformer;

    @Override
    public Mono<DetalleLibro> agregarDetalleLibro(DetalleLibro detalleLibro) {
        return repository.save(transformer.transformarADetalleLibroEntity(detalleLibro))
                .map(transformer::transformarADetalleLibro);
    }
}
