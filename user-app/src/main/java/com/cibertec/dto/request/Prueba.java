package com.cibertec.dto.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Prueba {
	private String nombre;
	private int edad;
}
