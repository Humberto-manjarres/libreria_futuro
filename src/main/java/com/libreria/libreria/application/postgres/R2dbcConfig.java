package com.libreria.libreria.application.postgres;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@Configuration
@EnableR2dbcRepositories(basePackages = "com.libreria.libreria.infraestructure.driven_adapters.r2dbc_repository")
public class R2dbcConfig {
}
