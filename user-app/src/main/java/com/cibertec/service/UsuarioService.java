package com.cibertec.service;

import java.util.List;

import com.cibertec.dto.request.ActualizarClaveDTO;
import com.cibertec.dto.request.UsuarioActualizarDTO;
import com.cibertec.dto.request.UsuarioCreacionDTO;
import com.cibertec.dto.response.SuccessResponse;
import com.cibertec.dto.response.UsuarioDTO;

public interface UsuarioService {
	SuccessResponse<List<UsuarioDTO>> listarUsuarios();
	SuccessResponse<UsuarioDTO> crearUsuario(UsuarioCreacionDTO usuario);
	SuccessResponse<UsuarioDTO> obtenerUsuarioPorId(Integer id);
	SuccessResponse<UsuarioDTO> actualizarCorreo(Integer id, UsuarioActualizarDTO usuario);
	SuccessResponse<String> actualizarClave(Integer id);
	SuccessResponse<String> eliminarUsuario(Integer id);
}
