package com.cibertec.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DistritoActualizarDTO {
    private String nombre;
    private Integer provinciaId;
    private Boolean isEnabled;
}
