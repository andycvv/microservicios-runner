package com.cibertec.dto.request;

import lombok.Data;

@Data
public class TrabajadorCreacionDTO {
	
	private Double salario;
	
	private int horasLaborales;
  
    private UsuarioCreacionDTO usuario;
    
    private Integer idTienda;
}
