package com.cibertec.user.dto.request;

import lombok.Data;

@Data
public class RegisterUserDTO {
	private String nombre;
	private String apellido;
	private String dni;
	private String email;
	private String telefono;
	private String clave;
	private int idDto;
}


