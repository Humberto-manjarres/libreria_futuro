package com.libreria.libreria.infraestructure.driven_adapters.r2dbc_repository.categoria.trasnformer;

import com.libreria.libreria.domain.model.categoria.Categoria;
import com.libreria.libreria.infraestructure.driven_adapters.r2dbc_repository.categoria.CategoriaEntity;
import org.mapstruct.Mapper;

@Mapper
public interface CategoriaR2dbcTransformer {
    CategoriaEntity transformarACategoriaEntity(Categoria categoria);
    Categoria transformarACategoria(CategoriaEntity categoriaEntity);
}
