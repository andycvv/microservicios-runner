package com.cibertec.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_transaccion")
public class Transaccion {
	
	@EmbeddedId
    private TransaccionId id;

	@Column(name = "unidades", nullable = false)
	private int unidades;
	
	@Column(name = "descuento", nullable = false, precision = 10, scale = 2)
    private Double descuento;

	@Column(name = "precio_unitario", nullable = false, precision = 10, scale = 2)
    private Double precioUnitario;
	
	@Column(name = "total", nullable = false, precision = 10, scale = 2)
    private Double total;

	@ManyToOne
	@JoinColumn(name = "id_boleta", referencedColumnName = "id_boleta", insertable = false, updatable = false)
	private Boleta boleta;
	
//	@ManyToOne
//	@JoinColumn(name = "id_producto", referencedColumnName = "id_producto", insertable = false, updatable = false)
//	private Producto producto;
}
