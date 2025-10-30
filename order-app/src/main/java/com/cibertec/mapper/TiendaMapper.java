package com.cibertec.mapper;

import org.springframework.stereotype.Service;

import com.cibertec.entity.Tienda;
import com.cibertec.response.TiendaDTO;

@Service
public class TiendaMapper {
	public TiendaDTO toTiendaDTO(Tienda tienda) {
		return TiendaDTO.builder()
				.id(tienda.getId())
				.rutaImagen(tienda.getRutaImagen())
				.nombre(tienda.getNombre())
				.nombreLegal(tienda.getNombreLegal())
				.direccion(tienda.getDireccion())
				.ruc(tienda.getRuc())
				.isEnabled(tienda.isEnabled())
				.build();
	}	
}
