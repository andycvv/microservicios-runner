package com.cibertec.user.dto.request;

import lombok.Data;

@Data
public class UpdateUserDTO {
	private String nombre;
	private String apellido;
	private String dni;
	private String telefono;
}
