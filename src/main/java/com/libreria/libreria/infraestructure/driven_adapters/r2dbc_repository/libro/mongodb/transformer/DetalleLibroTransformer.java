package com.libreria.libreria.infraestructure.driven_adapters.r2dbc_repository.libro.mongodb.transformer;

import com.libreria.libreria.domain.model.libro.DetalleLibro;
import com.libreria.libreria.infraestructure.driven_adapters.r2dbc_repository.libro.mongodb.DetalleLibroEntity;
import org.mapstruct.Mapper;

@Mapper
public interface DetalleLibroTransformer {
    DetalleLibroEntity transformarADetalleLibroEntity(DetalleLibro detalleLibro);
    DetalleLibro transformarADetalleLibro(DetalleLibroEntity libroEntity);
}
