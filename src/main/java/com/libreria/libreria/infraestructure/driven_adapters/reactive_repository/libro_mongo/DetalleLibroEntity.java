package com.libreria.libreria.infraestructure.driven_adapters.reactive_repository.libro_mongo;


import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Document(collection = "detalle_libro")
public class DetalleLibroEntity {
    private String id;
    private String codigoLibro;
    private String nombre;
    private Integer valorUnitario;
}
