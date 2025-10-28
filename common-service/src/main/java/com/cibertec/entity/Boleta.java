package com.cibertec.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_boleta")
public class Boleta {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "direccion", nullable = false, length = 100)
	private String direccion;
	
	@Column(name = "tipo_tarjeta", nullable = false, length = 100)
	private String tipoTarjeta;
	
	@Column(name = "total_descuento", nullable = false)
	private BigDecimal totalDescuento;
	
	@Column(name = "nombre_cliente", nullable = false, length = 100)
	private String nombreCliente;
	
	@Column(name = "numero_documento", nullable = false, length = 8)
	private String numeroDocumento;
	
	@Column(name = "subtotal", nullable = false)
	private BigDecimal subtotal;
	
	@Column(name = "igv", nullable = false)
	private BigDecimal igv = new BigDecimal("0.18");
	
	@Column(name = "total", nullable = false)
	private BigDecimal total;
	
	@Column(name = "estado", nullable = false, length = 100)
	private String estado;
	
	@Column(name = "observaciones", nullable = false, length = 100)
	private String observaciones;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario", nullable = false)
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name = "id_tienda", nullable = false)
	private Tienda tienda;

	@ManyToOne
	@JoinColumn(name = "id_trabajador", nullable = false)
	private Trabajador trabajador;
	
	@OneToMany(mappedBy = "boleta")
	private List<Transaccion> transaccion;
	
	@Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
}
