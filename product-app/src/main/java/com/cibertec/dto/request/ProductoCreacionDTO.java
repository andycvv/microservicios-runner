package com.cibertec.dto.request;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductoCreacionDTO {
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private Integer idCategoria;
    private Integer idSubcategoria;
    private Integer idBranch;
    private Integer idMaterial;
    private Integer idMarca;
}
