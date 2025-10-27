package com.cibertec.mapper;

import org.springframework.stereotype.Service;

import com.cibertec.dto.request.UsuarioCreacionDTO;
import com.cibertec.dto.response.UsuarioDTO;
import com.cibertec.entity.Departamento;
import com.cibertec.entity.Distrito;
import com.cibertec.entity.Pais;
import com.cibertec.entity.Provincia;
import com.cibertec.entity.Rol;
import com.cibertec.entity.Usuario;

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

	public Usuario toUsuario(Integer id, UsuarioCreacionDTO usuarioDTO, Rol rol, Pais pais, Departamento departamento, 
			Provincia provincia,Distrito distrito) {
		Usuario usuario = new Usuario();
		usuario.setId(id);
		usuario.setNombre(usuarioDTO.getNombre());
		usuario.setApellido(usuarioDTO.getApellido());
		usuario.setTipoDocumento(usuarioDTO.getTipoDocumento());
		usuario.setNmrDocumento(usuarioDTO.getNmrDocumento());
		usuario.setEmail(usuarioDTO.getEmail());
		usuario.setClave(usuarioDTO.getClave());
		usuario.setTelefono(usuarioDTO.getTelefono());
		usuario.setRol(rol);
		usuario.setPais(pais);
		usuario.setDepartamento(departamento);
		usuario.setProvincia(provincia);
		usuario.setDistrito(distrito);

		return usuario;
	}
}
