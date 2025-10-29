package com.cibertec.dto.request;

import lombok.Data;

@Data
public class TiendaCreacionDTO {
	private String nombre;
	private String nombreLegal;
	private String direccion;
	private String ruc;
	private String rutaImagen;
	private String ubicacion;
	private String telefono;
	private String mail;
	private Integer idPais;
	private Integer idDepartamento;
	private Integer idDistrito;
	private Integer idProvincia;
}
