package com.cibertec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	@GetMapping("/{id}")
	public ResponseEntity<SuccessResponse<TiendaDTO>> obtenerTiendaPorId(@PathVariable Integer id) {
		SuccessResponse<TiendaDTO> resp = tiendaService.obtenerTiendaPorId(id);
		return ResponseEntity.status(resp.getStatus()).body(resp);
	}
	
}
