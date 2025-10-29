package com.cibertec.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductoDTO {
    private Integer id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private String categoria;
    private String subcategoria;
    private String branch;
    private String material;
    private String marca;
    private Boolean isEnabled;
}