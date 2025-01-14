package com.libreria.libreria.infraestructure.entry_points.reactive_web.libro.transformers;

import com.libreria.libreria.domain.model.escritor.Escritor;
import com.libreria.libreria.domain.model.libro.Libro;
import com.libreria.libreria.infraestructure.entry_points.reactive_web.escritor.dto.EscritorDTO;
import com.libreria.libreria.infraestructure.entry_points.reactive_web.libro.dto.LibroDTO;
import org.mapstruct.Mapper;

@Mapper
public interface LibroTransformer {

    Libro transformarALibro(LibroDTO escritorDTO);
    LibroDTO transformarALibroDTO(Libro libro);
}
