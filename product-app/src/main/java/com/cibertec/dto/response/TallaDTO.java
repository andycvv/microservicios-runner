package com.cibertec.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TallaDTO {
    private Integer id;
    private Integer numero;
    private Boolean isEnabled;
}
