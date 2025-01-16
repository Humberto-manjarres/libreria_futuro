package com.libreria.libreria.infraestructure.entry_points.reactive_web.libro.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LibroDTO {
    private Integer id;
    private String nombre;
    private int numeroPaginas;
    private String idEscritor;
    private String descripcion;
    private Integer idCategoria;
    private Integer idEditorial;
}
