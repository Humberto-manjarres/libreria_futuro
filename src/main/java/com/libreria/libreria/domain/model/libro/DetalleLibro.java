package com.libreria.libreria.domain.model.libro;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DetalleLibro {
    private String id;
    private String codigoLibro;
    private String nombre;
    private Integer valorUnitario;
}
