package com.cibertec.user.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_distrito")
public class Distrito {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "id_dto")
	private Integer idDto;
	@Column (name = "nombre", nullable = false, length = 100, unique = true)
	private String nombre;
	
}
