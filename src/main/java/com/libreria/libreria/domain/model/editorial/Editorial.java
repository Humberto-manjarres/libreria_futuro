package com.libreria.libreria.domain.model.editorial;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Editorial {
    private Integer id;
    private String nombre;
    private String direccion;
}
