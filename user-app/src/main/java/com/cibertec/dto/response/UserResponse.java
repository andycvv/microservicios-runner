package com.cibertec.dto.response;

import com.cibertec.entity.Distrito;

import lombok.Data;

@Data
public class UserResponse {
	private String nombre;
	private String apellido;
	private String nmrDocumento;
	private String telefono;
	private String correo;
	private Distrito distrito;
}
