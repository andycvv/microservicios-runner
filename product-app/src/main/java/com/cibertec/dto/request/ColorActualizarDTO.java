package com.cibertec.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ColorActualizarDTO {
    private String nombre;
    private Boolean isEnabled;
}
