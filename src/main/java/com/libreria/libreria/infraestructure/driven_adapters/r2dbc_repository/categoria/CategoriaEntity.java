package com.libreria.libreria.infraestructure.driven_adapters.r2dbc_repository.categoria;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Builder
@Table(name = "categoria")
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaEntity {

    @Id
    @Column(value = "id")
    private Integer id;

    @Column(value = "nombre")
    private String nombre;
}
