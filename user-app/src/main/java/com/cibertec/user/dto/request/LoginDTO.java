package com.cibertec.user.dto.request;

import lombok.Data;

@Data
public class LoginDTO {
    private String correo;
    private String contrasenia;
}
