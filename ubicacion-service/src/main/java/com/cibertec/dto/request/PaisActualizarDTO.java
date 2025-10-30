package com.cibertec.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaisActualizarDTO {
    private String nombre;
    private String img;
    private String abreviatura;
    private String simbolo;
    private Boolean isEnabled;
}
