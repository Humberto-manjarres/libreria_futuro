package com.libreria.libreria.application;

import com.libreria.libreria.infraestructure.driven_adapters.r2dbc_repository.escritor.transformer.EscritorR2dbcTransformer;
import com.libreria.libreria.infraestructure.entry_points.reactive_web.escritor.transformers.EscritorTransformer;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransformerConfig {

    @Bean
    public EscritorR2dbcTransformer r2dbcTransformer() {
        return Mappers.getMapper(
                EscritorR2dbcTransformer.class);
    }

    @Bean
    public EscritorTransformer transformer() {
        return Mappers.getMapper(
                EscritorTransformer.class);
    }
}
