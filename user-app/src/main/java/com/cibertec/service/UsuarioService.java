package com.cibertec.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cibertec.dto.request.UsuarioActualizarCorreoDTO;
import com.cibertec.dto.request.UsuarioCreacionDTO;
import com.cibertec.dto.response.SuccessResponse;
import com.cibertec.dto.response.UsuarioDTO;

public interface UsuarioService {
	SuccessResponse<Page<UsuarioDTO>> listarUsuarios(Pageable pageable);
	SuccessResponse<Page<UsuarioDTO>> listarUsuariosActivos(Pageable pageable);
	SuccessResponse<UsuarioDTO> crearUsuario(UsuarioCreacionDTO usuario);
	SuccessResponse<UsuarioDTO> obtenerUsuarioPorId(Integer id);
	SuccessResponse<UsuarioDTO> obtenerUsuarioPorCorreo(String email);
	SuccessResponse<UsuarioDTO> actualizarCorreo(Integer id, UsuarioActualizarCorreoDTO usuario);
	SuccessResponse<String> actualizarClave(Integer id);
	SuccessResponse<String> eliminarUsuario(Integer id);
	SuccessResponse<String> cambiarEstado(Integer id);
}
