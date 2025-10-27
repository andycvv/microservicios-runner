package com.cibertec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.dto.request.UsuarioActualizarDTO;
import com.cibertec.dto.request.UsuarioCreacionDTO;
import com.cibertec.dto.response.SuccessResponse;
import com.cibertec.dto.response.UsuarioDTO;
import com.cibertec.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping
	public ResponseEntity<SuccessResponse<List<UsuarioDTO>>> listarUsuarios() {
		SuccessResponse<List<UsuarioDTO>> resp = usuarioService.listarUsuarios();
		return ResponseEntity.status(resp.getStatus()).body(resp);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<SuccessResponse<UsuarioDTO>> obtenerUsuarioPorId(
			@PathVariable Integer id
	) {
		SuccessResponse<UsuarioDTO> resp = usuarioService.obtenerUsuarioPorId(id);
		return ResponseEntity.status(resp.getStatus()).body(resp);
	}
	
	@PostMapping
	public ResponseEntity<SuccessResponse<UsuarioDTO>> crearUsuario(
			@RequestBody UsuarioCreacionDTO usuario
	) {
		SuccessResponse<UsuarioDTO> resp = usuarioService.crearUsuario(usuario);
		return ResponseEntity.status(resp.getStatus()).body(resp);
	}
	
	@PutMapping("/{id}/actualizar-correo")
	public ResponseEntity<SuccessResponse<UsuarioDTO>> actualizarCorreo(
			@PathVariable Integer id,
			@RequestBody UsuarioActualizarDTO usuario
	) {
		SuccessResponse<UsuarioDTO> resp = usuarioService.actualizarCorreo(id, usuario);
		return ResponseEntity.status(resp.getStatus()).body(resp);
	}
	
	@PutMapping("/{id}/actualizar-clave")
	public ResponseEntity<SuccessResponse<String>> actualizarClave(
			@PathVariable Integer id
	) {
		SuccessResponse<String> resp = usuarioService.actualizarClave(id);
		return ResponseEntity.status(resp.getStatus()).body(resp);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<SuccessResponse<String>> eliminarUsuario(
			@PathVariable Integer id
	) {
		SuccessResponse<String> resp = usuarioService.eliminarUsuario(id);
		return ResponseEntity.status(resp.getStatus()).body(resp);
	}
}