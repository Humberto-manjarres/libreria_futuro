package com.libreria.libreria.application;

import com.libreria.libreria.infraestructure.driven_adapters.r2dbc_repository.categoria.trasnformer.CategoriaR2dbcTransformer;
import com.libreria.libreria.infraestructure.driven_adapters.r2dbc_repository.editorial.transformer.EditorialR2dbcTransformer;
import com.libreria.libreria.infraestructure.driven_adapters.r2dbc_repository.escritor.transformer.EscritorR2dbcTransformer;
import com.libreria.libreria.infraestructure.driven_adapters.r2dbc_repository.libro.mongodb.transformer.DetalleLibroTransformer;
import com.libreria.libreria.infraestructure.driven_adapters.r2dbc_repository.libro.postgresdb.transformer.LibroR2dbcTransformer;
import com.libreria.libreria.infraestructure.entry_points.reactive_web.categoria.transformers.CategoriaTransformer;
import com.libreria.libreria.infraestructure.entry_points.reactive_web.editorial.transformers.EditorialTransformer;
import com.libreria.libreria.infraestructure.entry_points.reactive_web.escritor.transformers.EscritorTransformer;
import com.libreria.libreria.infraestructure.entry_points.reactive_web.libro.transformers.LibroTransformer;
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

    @Bean
    public CategoriaTransformer categoriaTransformer() {
        return Mappers.getMapper(
                CategoriaTransformer.class);
    }

    @Bean
    public CategoriaR2dbcTransformer categoriaR2dbcTransformer() {
        return Mappers.getMapper(
                CategoriaR2dbcTransformer.class);
    }

    @Bean
    public EditorialTransformer editorialTransformer() {
        return Mappers.getMapper(
                EditorialTransformer.class);
    }

    @Bean
    public EditorialR2dbcTransformer editorialR2dbcTransformer() {
        return Mappers.getMapper(
                EditorialR2dbcTransformer.class);
    }

    @Bean
    public LibroTransformer libroTransformer() {
        return Mappers.getMapper(
                LibroTransformer.class);
    }

    @Bean
    public LibroR2dbcTransformer libroR2dbcTransformer() {
        return Mappers.getMapper(
                LibroR2dbcTransformer.class);
    }

    @Bean
    public DetalleLibroTransformer detalleLibroTransformer() {
        return Mappers.getMapper(
                DetalleLibroTransformer.class);
    }
}
