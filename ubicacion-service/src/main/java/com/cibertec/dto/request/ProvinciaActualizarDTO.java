package com.cibertec.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProvinciaActualizarDTO {
    private String nombre;
    private Integer departamentoId;
    private Boolean isEnabled;
}
