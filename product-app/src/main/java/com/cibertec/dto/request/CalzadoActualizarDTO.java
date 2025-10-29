package com.cibertec.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CalzadoActualizarDTO {
    private Integer stock;
    private String estado;
    private Integer idProducto;
    private Integer idColor;
    private Integer idTalla;
    private Boolean isEnabled;
}
