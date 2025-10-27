package com.cibertec.user.dto.request;
import lombok.Data;

@Data
public class UpdatePasswordDTO {
    private String claveActual;
    private String nuevaClave;
}
