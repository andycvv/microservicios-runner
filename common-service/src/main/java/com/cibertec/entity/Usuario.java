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

@Entity
@Table(name = "tb_usuario")
@Data
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "nombre", nullable = false, length = 30)
	private String nombre;

	@Column(name = "apellido", nullable = false, length = 30)
	private String apellido;
	
	@Column(name = "tipo_documento", nullable = false, length = 30)
	private String tipoDocumento;

	@Column(name = "nmr_documento", nullable = false, length = 12)
	private String nmrDocumento;

	private String email;

	@Column(name = "clave", nullable = false, length = 100)
	private String clave;
	
	@Column(name = "telefono", nullable = false, length = 12)
	private String telefono;

	@ManyToOne
	@JoinColumn(name = "id_rol", nullable = false)
	private Rol rol;
	
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