package com.cibertec.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioDTO {
	private Integer id;
	private String nombre;
	private String apellido;
	private String tipoDocumento;
	private String nmrDocumento;
	private String email;
	private String telefono;
	private String rol;
	private String departamento;
	private String provincia;
	private String distrito;
}
