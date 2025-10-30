package com.cibertec.response;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class TransaccionDTO {
	private Integer id;
	private ProductoDTO producto;
	private int unidades;
	private BigDecimal descuento;
	private BigDecimal precioUnitario;
	private BigDecimal total;
}
