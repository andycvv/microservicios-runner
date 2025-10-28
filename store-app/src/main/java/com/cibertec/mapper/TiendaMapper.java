package com.cibertec.mapper;

import org.springframework.stereotype.Service;

import com.cibertec.dto.response.TiendaDTO;
import com.cibertec.entity.Tienda;

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
				.pais(tienda.getPais().getNombre())
				.departamento(tienda.getDepartamento().getNombre())
				.provincia(tienda.getProvincia().getNombre())
				.distrito(tienda.getDistrito().getNombre())
				.build();
	}
	
}
