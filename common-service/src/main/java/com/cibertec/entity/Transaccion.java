package com.cibertec.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "tb_transaccion")
public class Transaccion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

	@Column(name = "unidades", nullable = false)
	private int unidades;
	
	@Column(name = "descuento", nullable = false, precision = 10, scale = 2)
    private BigDecimal descuento = new BigDecimal("20");

	@Column(name = "precio_unitario", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioUnitario;
	
	@Column(name = "total", nullable = false, precision = 10, scale = 2)
    private BigDecimal total;

	@Column(name = "id_boleta", nullable = false)
	private Integer idBoleta;
	
	@ManyToOne
	@JoinColumn(name = "id_boleta", insertable = false, updatable = false)
	private Boleta boleta;

	@Column(name = "id_producto", nullable = false)
	private Integer idProducto;
	
	@ManyToOne
	@JoinColumn(name = "id_producto", insertable = false, updatable = false)
	private Producto producto;
}