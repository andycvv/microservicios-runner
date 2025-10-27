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
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_tienda")
public class Tienda {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "nombre", nullable = false, length = 100)
	private String nombre;
	
	@Column(name = "nombre_legal", nullable = false, length = 100)
	private String nombreLegal;
	
	@Column(name = "direccion", nullable = false, length = 100)
	private String direccion;
	
	@Column(name = "ruc", nullable = false, length = 100)
	private String ruc;
	
	@Column(name = "ubicacion", nullable = false, length = 100)
	private String ubicacion;
	
	@Column(name = "telefono", nullable = false, length = 9)
	private String telefono;
	
	@Column(name = "mail", nullable = false, length = 100)
	private String mail;
	
	@ManyToOne
	@JoinColumn(name = "id_pais", nullable = false)
	private Pais pais;
	
	@ManyToOne
	@JoinColumn(name = "id_departamento", nullable = false)
	private Departamento departamento;
	
	@ManyToOne
	@JoinColumn(name = "id_provincia", nullable = false)
	private Provincia provincia;
	
	@ManyToOne
	@JoinColumn(name = "id_distrito", nullable = false)
	private Distrito distrito;
	
	@Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
	
	@Column(name = "updated_at")
	@UpdateTimestamp
    private LocalDateTime updatedAt;
	
	@Column(name = "is_deleted", nullable = false)
	private Boolean isDeleted;
	
	@Column(name = "is_enabled", nullable = false)
	private Boolean isEnabled;
}
