package com.cibertec.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.client.TiendaClient;
import com.cibertec.client.UsuarioClient;
import com.cibertec.entity.Boleta;
import com.cibertec.response.BoletaDTO;
import com.cibertec.response.SuccessResponse;
import com.cibertec.response.TiendaDTO;
import com.cibertec.response.TrabajadorDTO;
import com.cibertec.response.UsuarioDTO;

@Service
public class BoletaMapper {
	@Autowired
	private UsuarioMapper usuarioMapper;
	@Autowired
	private TrabajadorMapper trabajadorMapper;
	@Autowired
	private TransaccionMapper transaccionMapper;
	@Autowired
	private TiendaClient tiendaClient;
	@Autowired
	private UsuarioClient usuarioClient;
	
	public BoletaDTO toBoletaDTO(Boleta boleta) {
		SuccessResponse<TiendaDTO> tiendaResponse = tiendaClient.obtenerTiendaPorId(boleta.getIdTienda());
		SuccessResponse<UsuarioDTO> usuarioResponse = usuarioClient.obtenerUsuarioPorId(boleta.getIdUsuario());
		SuccessResponse<TrabajadorDTO> trabajadorResponse = usuarioClient.obtenerTrabajadorPorId(boleta.getIdTrabajador());
		
		BoletaDTO dto = new BoletaDTO();
		dto.setId(boleta.getId());
		dto.setDireccion(boleta.getDireccion());
		dto.setTipoTarjeta(boleta.getTipoTarjeta());
		dto.setTotalDescuento(boleta.getTotalDescuento());
		dto.setNombreCliente(boleta.getNombreCliente());
		dto.setNumeroDocumento(boleta.getNumeroDocumento());
		dto.setSubtotal(boleta.getSubtotal());
		dto.setIgv(boleta.getIgv());
		dto.setTotal(boleta.getTotal());
		dto.setEstado(boleta.getEstado());
		dto.setObservaciones(boleta.getObservaciones());
		dto.setUsuario(usuarioResponse.getResponse());
		dto.setTienda(tiendaResponse.getResponse());
		dto.setTrabajador(trabajadorResponse.getResponse());
		dto.setCreatedAt(boleta.getCreatedAt());
		dto.setTransacciones(boleta.getTransacciones().stream().map(transaccionMapper::toTransaccionDTO).toList());
		return dto;
	}
}
