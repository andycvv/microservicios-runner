package com.cibertec.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductoActualizarDTO {
    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer idCategoria;
    private Integer idSubcategoria;
    private Integer idBranch;
    private Integer idMaterial;
    private Integer idMarca;
    private Boolean isEnabled;
}
