package com.cibertec.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class BoletaDTO {
	private Integer id;
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
	private UsuarioDTO usuario;
	private TiendaDTO tienda;
	private TrabajadorDTO trabajador;
	private LocalDateTime createdAt;
	private List<TransaccionDTO> transacciones;
}
