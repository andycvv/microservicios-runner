package com.cibertec.mapper;

import org.springframework.stereotype.Service;

import com.cibertec.entity.Usuario;
import com.cibertec.response.UsuarioDTO;

@Service
public class UsuarioMapper {
	public UsuarioDTO toUsuarioDTO(Usuario usuario) {
		return UsuarioDTO.builder()
				.id(usuario.getId())
				.nombre(usuario.getNombre())
				.apellido(usuario.getApellido())
				.tipoDocumento(usuario.getTipoDocumento())
				.nmrDocumento(usuario.getNmrDocumento())
				.email(usuario.getEmail())
				.telefono(usuario.getTelefono())
				.rol(usuario.getRol().getNombre())
				.pais(usuario.getPais().getNombre())
				.departamento(usuario.getDepartamento().getNombre())
				.provincia(usuario.getProvincia().getNombre())
				.distrito(usuario.getDistrito().getNombre())
				.build();
	}
}
