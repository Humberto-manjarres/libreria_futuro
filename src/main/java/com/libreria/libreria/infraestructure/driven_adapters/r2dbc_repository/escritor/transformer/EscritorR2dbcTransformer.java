package com.libreria.libreria.infraestructure.driven_adapters.r2dbc_repository.escritor.transformer;

import com.libreria.libreria.domain.model.escritor.Escritor;
import com.libreria.libreria.infraestructure.driven_adapters.r2dbc_repository.escritor.EscritorEntity;
import org.mapstruct.Mapper;

@Mapper
public interface EscritorR2dbcTransformer {
    EscritorEntity transformarAEscritorioEntity(Escritor escritor);
    Escritor transformarAEscritorio(EscritorEntity escritorEntity);
}
