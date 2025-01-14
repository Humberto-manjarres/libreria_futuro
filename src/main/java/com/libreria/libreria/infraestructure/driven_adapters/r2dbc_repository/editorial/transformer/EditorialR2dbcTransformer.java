package com.libreria.libreria.infraestructure.driven_adapters.r2dbc_repository.editorial.transformer;

import com.libreria.libreria.domain.model.editorial.Editorial;
import com.libreria.libreria.infraestructure.driven_adapters.r2dbc_repository.editorial.EditorialEntity;
import org.mapstruct.Mapper;

@Mapper
public interface EditorialR2dbcTransformer {
    EditorialEntity transformarAEditorialEntity(Editorial editorial);
    Editorial transformarAEditorial(EditorialEntity editorialEntity);
}
