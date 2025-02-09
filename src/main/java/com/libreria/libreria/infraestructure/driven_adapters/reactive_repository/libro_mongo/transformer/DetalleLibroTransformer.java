package com.libreria.libreria.infraestructure.driven_adapters.reactive_repository.libro_mongo.transformer;

import com.libreria.libreria.domain.model.libro.DetalleLibro;
import com.libreria.libreria.infraestructure.driven_adapters.reactive_repository.libro_mongo.DetalleLibroEntity;
import org.mapstruct.Mapper;

@Mapper
public interface DetalleLibroTransformer {
    DetalleLibroEntity transformarADetalleLibroEntity(DetalleLibro detalleLibro);
    DetalleLibro transformarADetalleLibro(DetalleLibroEntity libroEntity);
}
