package com.cibertec.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubcategoriaDTO {
    private Integer id;
    private String nombre;
    private Integer idCategoria;
    private Boolean isEnabled;
}
