package com.libreria.libreria.infraestructure.driven_adapters.r2dbc_repository.libro.transformer;

import com.libreria.libreria.domain.model.libro.Libro;
import com.libreria.libreria.infraestructure.driven_adapters.r2dbc_repository.libro.LibroEntity;
import org.mapstruct.Mapper;

@Mapper
public interface LibroR2dbcTransformer {
    LibroEntity transformarAEditorialEntity(Libro libro);
    Libro transformarALibro(LibroEntity libroEntity);
}
