package com.cibertec.response;

import lombok.Data;

@Data
public class TrabajadorDTO {
	private Integer id;
	private Double salario;
	private int horasLaborales;
	private String nombre;
	private String apellido;
	private String email;
	private boolean isEnabled;
}
