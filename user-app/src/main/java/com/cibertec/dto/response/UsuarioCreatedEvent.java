package com.cibertec.dto.response;

import lombok.Builder;
import lombok.Data;

@Builder
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
