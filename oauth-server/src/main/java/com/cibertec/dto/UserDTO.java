package com.cibertec.dto;

import lombok.Data;

@Data
public class UserDTO {
    private String correo;
    private String contrasenia;
    private String rol;
    private boolean estado;
}
