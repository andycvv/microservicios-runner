package com.cibertec.user.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

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
@Table(name = "tb_usuario")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usr")
	private Integer id;

	@Column(name = "nombre", nullable = false, length = 30)
	private String nombre;

	@Column(name = "apellido", nullable = false, length = 30)
	private String apellido;

	@Column(name = "nmr_documento", nullable = false, length = 12)
	private String nmrDocumento;

	@Column(name = "telefono", nullable = false, length = 12)
	private String telefono;

	@Column(name = "correo", nullable = false, length = 50)
	private String correo;

	@Column(name = "contrasenia", nullable = false, length = 100)
	private String contrasenia;

	@Column(name = "rol", nullable = false, length = 10)
	private String rol;

    @Column(name = "estado", nullable = false)
    private boolean estado;
    
    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_eliminacion")
    private LocalDateTime fechaEliminacion;

	@Column(name = "id_dto", nullable = false)
	private Integer idDto;
	
	@ManyToOne
	@JoinColumn(name = "id_dto", referencedColumnName = "id_dto", insertable = false, updatable = false)
    private Distrito distrito;
}
