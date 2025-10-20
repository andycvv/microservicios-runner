package com.cibertec.user.dto.request;


import com.cibertec.user.model.Usuario;

import lombok.Data;

@Data
public class TrabajadorDTO {
	
	private Double salario;
	
	private int horasLaborales;
  
    private Usuario usuario;
}
