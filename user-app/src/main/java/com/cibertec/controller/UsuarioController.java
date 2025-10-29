package com.cibertec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.dto.request.UsuarioActualizarCorreoDTO;
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
	public ResponseEntity<SuccessResponse<Page<UsuarioDTO>>> listarUsuarios(
    		@PageableDefault(page = 0, size = 10) Pageable pageable
    ) {
		SuccessResponse<Page<UsuarioDTO>> resp = usuarioService.listarUsuarios(pageable);
		return ResponseEntity.status(resp.getStatus()).body(resp);
	}
	
	@GetMapping("/activos")
	public ResponseEntity<SuccessResponse<Page<UsuarioDTO>>> listarUsuariosActivos(
    		@PageableDefault(page = 0, size = 10) Pageable pageable
    ) {
		SuccessResponse<Page<UsuarioDTO>> resp = usuarioService.listarUsuariosActivos(pageable);
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
			@RequestBody UsuarioActualizarCorreoDTO usuario
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
    
    @PostMapping("/{id}/cambiar-estado")
    public ResponseEntity<SuccessResponse<String>> cambiarEstado(@PathVariable Integer id) {
    	SuccessResponse<String> resp = usuarioService.cambiarEstado(id);
		return ResponseEntity.status(resp.getStatus()).body(resp);
	}
}