package com.libreria.libreria.infraestructure.driven_adapters.r2dbc_repository.libro;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Builder
@Table(name = "libro")
@NoArgsConstructor
@AllArgsConstructor
public class LibroEntity {
    @Id
    @Column(value = "id")
    private Integer id;

    @Column(value = "nombre")
    private String nombre;

    @Column(value = "id_escritor")
    private String idEscritor;

    @Column(value = "descripcion")
    private String descripcion;

    @Column(value = "id_categoria")
    private String idCategoria;

    @Column(value = "id_editorial")
    private String idEditorial;
}
