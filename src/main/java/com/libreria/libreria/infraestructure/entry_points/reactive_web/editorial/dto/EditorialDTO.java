package com.libreria.libreria.infraestructure.entry_points.reactive_web.editorial.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EditorialDTO {
    private Integer id;
    private String nombre;
    private String direccion;
}
