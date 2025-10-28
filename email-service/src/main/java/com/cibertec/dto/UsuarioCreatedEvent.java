package com.cibertec.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UsuarioCreatedEvent {
	private String nombre;
	private String apellido;
	private String tipoDocumento;
	private String nmrDocumento;
	private String email;
	private String telefono;
	private String rol;
	
	private String clave;
}
