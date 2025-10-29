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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
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
	
	@Column(name = "ruta_imagen", nullable = false, length = 200)
	private String rutaImagen;
	
	@Column(name = "ubicacion", nullable = false, length = 100)
	private String ubicacion;
	
	@Column(name = "telefono", nullable = false, length = 9)
	private String telefono;
	
	@Column(name = "mail", nullable = false, length = 100)
	private String mail;
	
	@Column(name = "id_pais", nullable = false)
	private Integer idPais;
	
	@ManyToOne
	@JoinColumn(name = "id_pais", insertable = false, updatable = false)
	private Pais pais;
	
	@Column(name = "id_departamento", nullable = false)
	private Integer idDepartamento;
	
	@ManyToOne
	@JoinColumn(name = "id_departamento", insertable = false, updatable = false)
	private Departamento departamento;
	
	@Column(name = "id_provincia", nullable = false)
	private Integer idProvincia;
	
	@ManyToOne
	@JoinColumn(name = "id_provincia", insertable = false, updatable = false)
	private Provincia provincia;
	
	@Column(name = "id_distrito", nullable = false)
	private Integer idDistrito;
	
	@ManyToOne
	@JoinColumn(name = "id_distrito", insertable = false, updatable = false)
	private Distrito distrito;
	
	@Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
	
	@Column(name = "updated_at")
	@UpdateTimestamp
    private LocalDateTime updatedAt;
	
	@Column(name = "is_deleted", nullable = false)
	private boolean isDeleted = false;
	
	@Column(name = "is_enabled", nullable = false)
	private boolean isEnabled = true;
}
