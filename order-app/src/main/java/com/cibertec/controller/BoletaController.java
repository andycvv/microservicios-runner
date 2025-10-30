package com.cibertec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.dto.request.BoletaCreacionDTO;
import com.cibertec.dto.response.PaginacionResponse;
import com.cibertec.entity.Boleta;
import com.cibertec.response.BoletaDTO;
import com.cibertec.response.SuccessResponse;
import com.cibertec.service.BoletaService;

@RestController
@RequestMapping("/api/boletas")
public class BoletaController {

	@Autowired
	private BoletaService boletaService;
	
	@GetMapping
	public ResponseEntity<SuccessResponse<PaginacionResponse<BoletaDTO>>> listarBoletas(
    		@PageableDefault(page = 0, size = 10) Pageable pageable
    ) {
		SuccessResponse<PaginacionResponse<BoletaDTO>> resp = boletaService.listarBoletas(pageable);
		return ResponseEntity.status(resp.getStatus()).body(resp);
	}
	
	@GetMapping("/trabajador/{id}")
	public ResponseEntity<SuccessResponse<PaginacionResponse<BoletaDTO>>> listarBoletasPorTrabajadorId(
			@PathVariable Integer id,
			@PageableDefault(page = 0, size = 10) Pageable pageable
		) {
		SuccessResponse<PaginacionResponse<BoletaDTO>> resp = boletaService.listarBoletasPorTrabajadorId(id, pageable);
		return ResponseEntity.status(resp.getStatus()).body(resp);
	}
	
	@GetMapping("/cliente/{id}")
	public ResponseEntity<SuccessResponse<PaginacionResponse<BoletaDTO>>> listarBoletasPorClienteId(
			@PathVariable Integer id,
			@PageableDefault(page = 0, size = 10) Pageable pageable
		) {
		SuccessResponse<PaginacionResponse<BoletaDTO>> resp = boletaService.listarBoletasPorClienteId(id, pageable);
		return ResponseEntity.status(resp.getStatus()).body(resp);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<SuccessResponse<BoletaDTO>> obtenerBoletaPorId(@PathVariable Integer id) {
		SuccessResponse<BoletaDTO> resp = boletaService.obtenerBoletaPorId(id);
		return ResponseEntity.status(resp.getStatus()).body(resp);
	}
	
	@PostMapping
	public ResponseEntity<SuccessResponse<String>> crearBoleta(@RequestBody BoletaCreacionDTO creacionDTO) {
		SuccessResponse<String> resp = boletaService.crearBoleta(creacionDTO);
		return ResponseEntity.status(resp.getStatus()).body(resp);
	}
}