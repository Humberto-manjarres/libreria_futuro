package com.libreria.libreria.application;

import com.libreria.libreria.domain.model.escritor.gateway.EscritorGateway;
import com.libreria.libreria.domain.usecase.categoria.CategoriaUseCase;
import com.libreria.libreria.domain.usecase.editorial.EditorialUseCase;
import com.libreria.libreria.domain.usecase.escritor.EscritorUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public EscritorUseCase escritorUseCase(EscritorGateway escritorGateway){
        return new EscritorUseCase(escritorGateway);
    }

    @Bean
    public EditorialUseCase editorialUseCase(){
        return new EditorialUseCase();
    }

    @Bean
    public CategoriaUseCase categoriaUseCase(){
        return new CategoriaUseCase();
    }

    /*@Bean
    public ErrorWebExceptionHandler globalExceptionHandler() {
        return new GlobalExceptionHandler();
    }*/


}
