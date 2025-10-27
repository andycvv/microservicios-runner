package com.cibertec.dto.request;

import lombok.Data;

@Data
public class ActualizarClaveDTO {
    private String claveActual;
    private String nuevaClave;
}
