package com.cibertec.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CalzadoDTO {
    private Integer id;
    private Integer stock;
    private String estado;
    private Integer idProducto;
    private Integer idColor;
    private Integer idTalla;
    private Boolean isEnabled;
}
