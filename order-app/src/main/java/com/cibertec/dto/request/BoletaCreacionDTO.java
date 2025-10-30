package com.cibertec.dto.request;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data 
public class BoletaCreacionDTO {
	private String direccion;
	private String tipoTarjeta;
	private BigDecimal totalDescuento;
	private String nombreCliente;
	private String numeroDocumento;
	private BigDecimal subtotal;
	private BigDecimal igv;
	private BigDecimal total;
	private String estado;
	private String observaciones;
	private Integer idUsuario;
	private Integer idTienda;
	private Integer idTrabajador;
	private List<TransaccionCreacionDTO> transacciones;
}
