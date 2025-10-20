package com.cibertec.user.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_trabajador")
public class Trabajador {
	
	@Id
	@Column(name = "id_tbj", nullable = false)
	private Integer id;
	
	@Column(name = "salario", nullable = false)
	private Double salario;
	
	@Column(name = "estado", nullable = false, length = 1)
	private String estado;

	@Column(name = "horas_laborales", nullable = false)
	private int horasLaborales;

    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime fechaCreacion;
    
    @Column(name = "fecha_salida", nullable = true, updatable = true)
    private LocalDateTime fechaSalida;
    
    @OneToOne
//    @MapsId
    @JoinColumn(name = "id_tbj", referencedColumnName = "id_usr", insertable = false, updatable = false)
    private Usuario usuario;
}
