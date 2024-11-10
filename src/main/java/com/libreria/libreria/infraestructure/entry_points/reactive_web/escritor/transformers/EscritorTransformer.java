package com.libreria.libreria.infraestructure.entry_points.reactive_web.escritor.transformers;


import com.libreria.libreria.domain.model.escritor.Escritor;
import com.libreria.libreria.infraestructure.entry_points.reactive_web.escritor.dto.EscritorDTO;
import org.mapstruct.Mapper;

@Mapper
public interface EscritorTransformer {

    Escritor transformarAEscrito(EscritorDTO escritorDTO);
    EscritorDTO transformarAEscritoDTO(Escritor escritor);
}
