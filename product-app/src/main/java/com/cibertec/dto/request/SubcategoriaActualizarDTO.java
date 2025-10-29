package com.cibertec.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubcategoriaActualizarDTO {
    private String nombre;
    private Integer idCategoria;
    private Boolean isEnabled;
}
