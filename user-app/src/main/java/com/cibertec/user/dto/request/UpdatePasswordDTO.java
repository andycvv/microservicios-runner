package com.cibertec.user.dto.request;
import lombok.Data;

@Data
public class UpdatePasswordDTO {
    private String contraseniaActual;
    private String nuevaContrasenia;
}
