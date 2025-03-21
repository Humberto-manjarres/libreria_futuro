package com.libreria.libreria.domain.model.categoria;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Categoria {
    private Integer id;
    private String nombre;
}
