package com.libreria.libreria.infraestructure.entry_points.reactive_web.libro.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DetalleLibroDTO {
    private String id;
    private String codigoLibro;
    private String nombre;
    private Integer valorUnitario;
}
