package com.cibertec.mapper;

import org.springframework.stereotype.Service;

import com.cibertec.dto.request.TiendaActualizarDTO;
import com.cibertec.dto.request.TiendaCreacionDTO;
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
				.isEnabled(tienda.isEnabled())
				.build();
	}
	
	public Tienda toTienda(TiendaCreacionDTO creacionDTO) {
		Tienda tienda = new Tienda();
		tienda.setNombre(creacionDTO.getNombre());
		tienda.setNombreLegal(creacionDTO.getNombreLegal());
		tienda.setDireccion(creacionDTO.getDireccion());
		tienda.setRuc(creacionDTO.getRuc());
		tienda.setRutaImagen(creacionDTO.getRutaImagen());
		tienda.setUbicacion(creacionDTO.getUbicacion());
		tienda.setTelefono(creacionDTO.getTelefono());
		tienda.setMail(creacionDTO.getMail());
		tienda.setIdPais(creacionDTO.getIdPais());
		tienda.setIdDepartamento(creacionDTO.getIdDepartamento());
		tienda.setIdDistrito(creacionDTO.getIdDistrito());
		tienda.setIdProvincia(creacionDTO.getIdProvincia());
		
		return tienda;
	}
	
	
	public Tienda toTienda(Integer id, TiendaActualizarDTO actualizarDTO) {
		Tienda tienda = new Tienda();
		tienda.setId(id);
		tienda.setNombre(actualizarDTO.getNombre());
		tienda.setNombreLegal(actualizarDTO.getNombreLegal());
		tienda.setDireccion(actualizarDTO.getDireccion());
		tienda.setRuc(actualizarDTO.getRuc());
		tienda.setRutaImagen(actualizarDTO.getRutaImagen());
		tienda.setUbicacion(actualizarDTO.getUbicacion());
		tienda.setTelefono(actualizarDTO.getTelefono());
		tienda.setMail(actualizarDTO.getMail());
		tienda.setIdPais(actualizarDTO.getIdPais());
		tienda.setIdDepartamento(actualizarDTO.getIdDepartamento());
		tienda.setIdDistrito(actualizarDTO.getIdDistrito());
		tienda.setIdProvincia(actualizarDTO.getIdProvincia());
		tienda.setEnabled(actualizarDTO.isEnabled());
		
		return tienda;
	}
	
}
