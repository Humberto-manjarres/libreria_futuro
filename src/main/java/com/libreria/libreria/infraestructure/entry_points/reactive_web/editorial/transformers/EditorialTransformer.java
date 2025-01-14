package com.libreria.libreria.infraestructure.entry_points.reactive_web.editorial.transformers;

import com.libreria.libreria.domain.model.editorial.Editorial;
import com.libreria.libreria.infraestructure.entry_points.reactive_web.editorial.dto.EditorialDTO;
import org.mapstruct.Mapper;

@Mapper
public interface EditorialTransformer {
    Editorial transformarAEditorial(EditorialDTO editorialDTO);
    EditorialDTO transformarAEditorialDTO(Editorial editorial);
}
