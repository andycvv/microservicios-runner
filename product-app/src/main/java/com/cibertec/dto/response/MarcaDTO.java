package com.cibertec.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MarcaDTO {
    private Integer id;
    private String nombre;
    private Boolean isEnabled;
}
