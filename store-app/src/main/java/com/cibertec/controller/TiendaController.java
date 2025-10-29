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

import com.cibertec.dto.request.TiendaActualizarDTO;
import com.cibertec.dto.request.TiendaCreacionDTO;
import com.cibertec.dto.response.SuccessResponse;
import com.cibertec.dto.response.TiendaDTO;
import com.cibertec.service.TiendaService;

@RestController
@RequestMapping("/api/tienda")
public class TiendaController {

	@Autowired
    private TiendaService tiendaService;
	
	@GetMapping
	public ResponseEntity<SuccessResponse<List<TiendaDTO>>> listarTiendas() {
		SuccessResponse<List<TiendaDTO>> resp = tiendaService.listarTiendas();
		return ResponseEntity.status(resp.getStatus()).body(resp);
	}
	
	@GetMapping("/activos")
	public ResponseEntity<SuccessResponse<List<TiendaDTO>>> listarTiendasActivas() {
		SuccessResponse<List<TiendaDTO>> resp = tiendaService.listarTiendasActivas();
		return ResponseEntity.status(resp.getStatus()).body(resp);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<SuccessResponse<TiendaDTO>> obtenerTiendaPorId(@PathVariable Integer id) {
		SuccessResponse<TiendaDTO> resp = tiendaService.obtenerTiendaPorId(id);
		return ResponseEntity.status(resp.getStatus()).body(resp);
	}
	
	@PostMapping
	public ResponseEntity<SuccessResponse<TiendaDTO>> crearTienda(@RequestBody TiendaCreacionDTO creacionDTO) {
		SuccessResponse<TiendaDTO> resp = tiendaService.crearTienda(creacionDTO);
		return ResponseEntity.status(resp.getStatus()).body(resp);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<SuccessResponse<TiendaDTO>> actualizarTienda(@PathVariable Integer id ,@RequestBody TiendaActualizarDTO actualizarDTO) {
		SuccessResponse<TiendaDTO> resp = tiendaService.actualizarTienda(id, actualizarDTO);
		return ResponseEntity.status(resp.getStatus()).body(resp);
	}
	
	@PutMapping("/{id}/cambiar-estado")
	public ResponseEntity<SuccessResponse<String>> cambiarEstado(@PathVariable Integer id) {
		SuccessResponse<String> resp = tiendaService.cambiarEstado(id);
		return ResponseEntity.status(resp.getStatus()).body(resp);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<SuccessResponse<String>> eliminarTienda(@PathVariable Integer id) {
		SuccessResponse<String> resp = tiendaService.eliminarTienda(id);
		return ResponseEntity.status(resp.getStatus()).body(resp);
	}
	
}
