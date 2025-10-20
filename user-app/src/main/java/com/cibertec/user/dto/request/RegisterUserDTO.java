package com.cibertec.user.dto.request;

import lombok.Data;

@Data
public class RegisterUserDTO {
	private String nombre;
	private String apellido;
	private String nmrDocumento;
	private String telefono;
	private String correo;
	private String contrasenia;
	private int idDto;
}


