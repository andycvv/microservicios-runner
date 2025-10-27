package com.cibertec.dto.request;

import com.cibertec.entity.Usuario;

import lombok.Data;

@Data
public class TrabajadorDTO {
	
	private Double salario;
	
	private int horasLaborales;
  
    private UsuarioCreacionDTO usuario;
    
    private Integer idTienda;
}
