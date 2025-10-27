package com.cibertec.user.service;

import java.util.List;

import com.cibertec.user.dto.request.ActualizarClaveDTO;
import com.cibertec.user.dto.request.UsuarioActualizarDTO;
import com.cibertec.user.dto.request.UsuarioCreacionDTO;
import com.cibertec.user.dto.response.SuccessResponse;
import com.cibertec.user.dto.response.UsuarioDTO;

public interface UsuarioService {
	SuccessResponse<List<UsuarioDTO>> listarUsuarios();
	SuccessResponse<UsuarioDTO> crearUsuario(UsuarioCreacionDTO usuario);
	SuccessResponse<UsuarioDTO> obtenerUsuarioPorId(Integer id);
	SuccessResponse<UsuarioDTO> actualizarCorreo(Integer id, UsuarioActualizarDTO usuario);
	SuccessResponse<String> actualizarClave(Integer id);
	SuccessResponse<String> eliminarUsuario(Integer id);
}
