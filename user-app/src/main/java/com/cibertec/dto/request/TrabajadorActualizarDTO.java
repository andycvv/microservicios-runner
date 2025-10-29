package com.cibertec.dto.request;

import lombok.Data;

@Data
public class TrabajadorActualizarDTO {
	
	private Double salario;
	
	private int horasLaborales;
    
    private Integer idTienda;
    
    private Boolean isEnabled;
}
