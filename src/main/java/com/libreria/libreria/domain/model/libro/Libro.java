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
    private int id;
    private String nombre;
    private Categoria categoria;
    private Editorial editorial;
}
