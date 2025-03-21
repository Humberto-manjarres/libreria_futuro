package com.libreria.libreria.domain.model.escritor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Escritor {
    private Integer id;
    private String identificacion;
    private String nombre;
}
