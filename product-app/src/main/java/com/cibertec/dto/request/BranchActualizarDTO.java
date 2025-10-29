package com.cibertec.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BranchActualizarDTO {
    private String nombre;
    private Integer idSubcategoria;
    private Boolean isEnabled;
}
