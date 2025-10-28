package com.cibertec.dto.request;

import lombok.Data;

@Data
public class TiendaActualizarDTO {
	private String nombre;
	private String nombreLegal;
	private String direccion;
	private String ruc;
	private String rutaImagen;
	private Integer idPais;
	private Integer idDepartamento;
	private Integer idDistrito;
	private Integer idProvincia;
}
