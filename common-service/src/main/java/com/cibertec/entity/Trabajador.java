package com.cibertec.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tb_trabajador")
@Data
public class Trabajador {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Double salario;
	private int horasLaborales;

	@Column(name = "id_usuario", nullable = false)
	private Integer idUsuario;

	@OneToOne
	@JoinColumn(name = "id_usuario", insertable = false, updatable = false)
	private Usuario usuario;

	@Column(name = "id_tienda", nullable = false)
	private Integer idTienda;

	@ManyToOne
	@JoinColumn(name = "id_tienda", insertable = false, updatable = false)
	private Tienda tienda;

	@Column(nullable = false, updatable = false)
	@CreationTimestamp
	private LocalDateTime createdAt;

	@Column(updatable = true)
	@UpdateTimestamp
	private LocalDateTime updatedAt;

	@Column(nullable = false)
	private boolean isDelete = false;

	@Column(nullable = false)
	private boolean isEnabled = true;
}