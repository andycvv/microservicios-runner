package com.cibertec.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TiendaDTO {
	private Integer id;
	private String rutaImagen;
	private String nombre;
	private String nombreLegal;
	private String direccion;
	private String ruc;
	private String pais;
	private String departamento;
	private String provincia;
	private String distrito;
	private boolean isEnabled;
}
