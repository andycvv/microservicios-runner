package com.cibertec.dto.request;

import lombok.Data;

@Data
public class UsuarioCreacionDTO {
	private String nombre;
	private String apellido;
	private String tipoDocumento;
	private String nmrDocumento;
	private String email;
	private String clave;
	private String telefono;
	private Integer idRol;
	private Integer idDepartamento;
	private Integer idProvincia;
	private Integer idDistrito;
}
