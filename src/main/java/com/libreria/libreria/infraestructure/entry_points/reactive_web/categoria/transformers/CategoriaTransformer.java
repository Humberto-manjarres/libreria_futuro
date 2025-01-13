package com.libreria.libreria.infraestructure.entry_points.reactive_web.categoria.transformers;

import com.libreria.libreria.domain.model.categoria.Categoria;
import com.libreria.libreria.infraestructure.entry_points.reactive_web.categoria.dto.CategoriaDTO;
import org.mapstruct.Mapper;

@Mapper
public interface CategoriaTransformer {
    Categoria transformarACategoria(CategoriaDTO caategoriaDTO);
    CategoriaDTO transformarACategoriaDTO(Categoria categoria);
}
