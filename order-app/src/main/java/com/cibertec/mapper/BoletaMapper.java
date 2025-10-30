package com.cibertec.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.entity.Boleta;
import com.cibertec.response.BoletaDTO;

@Service
public class BoletaMapper {
	
	@Autowired
	private UsuarioMapper usuarioMapper;
	
	@Autowired
	private TiendaMapper tiendaMapper;
	
	@Autowired
	private TrabajadorMapper trabajadorMapper;
	
	@Autowired
	private TransaccionMapper transaccionMapper;
	
	public BoletaDTO toBoletaDTO(Boleta boleta) {
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
		dto.setUsuario(usuarioMapper.toUsuarioDTO(boleta.getUsuario()));
		dto.setTienda(tiendaMapper.toTiendaDTO(boleta.getTienda()));
		dto.setTrabajador(trabajadorMapper.toDto(boleta.getTrabajador()));
		dto.setCreatedAt(boleta.getCreatedAt());
		dto.setTransacciones(boleta.getTransacciones().stream().map(transaccionMapper::toTransaccionDTO).toList());
		
		return dto;
		
	}
}
