package com.libreria.libreria.application;

import com.libreria.libreria.domain.model.categoria.gateway.CategoriaGateway;
import com.libreria.libreria.domain.model.editorial.gateway.EditorialGateway;
import com.libreria.libreria.domain.model.escritor.gateway.EscritorGateway;
import com.libreria.libreria.domain.model.libro.gateway.DetalleLibroGateway;
import com.libreria.libreria.domain.model.libro.gateway.LibroConsumerGateway;
import com.libreria.libreria.domain.model.libro.gateway.LibroGateway;
import com.libreria.libreria.domain.model.libro.gateway.LibroQueueGateway;
import com.libreria.libreria.domain.model.s3.S3FileGateway;
import com.libreria.libreria.domain.usecase.categoria.CategoriaUseCase;
import com.libreria.libreria.domain.usecase.editorial.EditorialUseCase;
import com.libreria.libreria.domain.usecase.escritor.EscritorUseCase;
import com.libreria.libreria.domain.usecase.libro.LibroUseCase;
import com.libreria.libreria.domain.usecase.s3.S3FileUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public EscritorUseCase escritorUseCase(EscritorGateway escritorGateway){
        return new EscritorUseCase(escritorGateway);
    }

    @Bean
    public EditorialUseCase editorialUseCase(EditorialGateway gateway){
        return new EditorialUseCase(gateway);
    }

    @Bean
    public CategoriaUseCase categoriaUseCase(CategoriaGateway categoriaGateway){
        return new CategoriaUseCase(categoriaGateway);
    }

    @Bean
    public LibroUseCase libroUseCase(LibroGateway libroGateway, LibroConsumerGateway libroConsumerGateway, LibroQueueGateway libroQueueGateway, DetalleLibroGateway detalleLibroGateway, EscritorUseCase escritorUseCase, CategoriaUseCase categoriaUseCase, EditorialUseCase editorialUseCase){
        return new LibroUseCase(libroGateway,libroConsumerGateway,libroQueueGateway,detalleLibroGateway,escritorUseCase,categoriaUseCase,editorialUseCase);
    }

    @Bean
    public S3FileUseCase s3FileUseCase(S3FileGateway s3FileGateway){
        return new S3FileUseCase(s3FileGateway);
    }

    /*@Bean
    public ErrorWebExceptionHandler globalExceptionHandler() {
        return new GlobalExceptionHandler();
    }*/


}
