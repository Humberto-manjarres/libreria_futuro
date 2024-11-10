package com.libreria.libreria.infraestructure.entry_points.reactive_web.escritor.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EscritorDTO {
    private Integer id;
    private String identificacion;
    private String nombre;
}
