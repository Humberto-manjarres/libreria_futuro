package com.libreria.libreria.domain.model.libro;

import com.libreria.libreria.domain.model.categoria.Categoria;
import com.libreria.libreria.domain.model.editorial.Editorial;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Libro {
    private Integer id;
    private String nombre;
    private String idEscritor;
    private String descripcion;
    private Categoria categoria;
    private Editorial editorial;
}
