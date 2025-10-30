package com.cibertec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.dto.request.TrabajadorActualizarDTO;
import com.cibertec.dto.request.TrabajadorCreacionDTO;
import com.cibertec.dto.response.PaginacionResponse;
import com.cibertec.dto.response.SuccessResponse;
import com.cibertec.dto.response.TrabajadorDTO;
import com.cibertec.service.TrabajadorService;

@RestController
@RequestMapping("/api/trabajadores")
public class TrabajadorController {

    @Autowired
    private TrabajadorService trabajadorService;

    @GetMapping
    public SuccessResponse<PaginacionResponse<TrabajadorDTO>> listarTodos(
    		@PageableDefault(page = 0, size = 10) Pageable pageable
    ) {
        return trabajadorService.listarTodos(pageable);
    }

    @GetMapping("/activos")
    public SuccessResponse<PaginacionResponse<TrabajadorDTO>> listarActivos(
    		@PageableDefault(page = 0, size = 10) Pageable pageable
    ){
        return trabajadorService.listarActivos(pageable);
    }

    @GetMapping("/inactivos")
    public SuccessResponse<PaginacionResponse<TrabajadorDTO>> listarInactivos(
    		@PageableDefault(page = 0, size = 10) Pageable pageable
    ) {
        return trabajadorService.listarInactivos(pageable);
    }

    @GetMapping("/{id}")
    public SuccessResponse<TrabajadorDTO> obtenerPorId(@PathVariable Integer id) {
        return trabajadorService.obtenerPorId(id);
    }

    @PostMapping
    public SuccessResponse<TrabajadorDTO> registrar(@RequestBody TrabajadorCreacionDTO trabajador) {
        return trabajadorService.registrar(trabajador);
    }
    
    @PutMapping("/{id}")
    public SuccessResponse<TrabajadorDTO> actualizar(@PathVariable Integer id,
    		@RequestBody TrabajadorActualizarDTO trabajadorDTO) {
		return trabajadorService.actualizar(id, trabajadorDTO);
	}

    @DeleteMapping("/{id}")
    public SuccessResponse<String> eliminarLogico(@PathVariable Integer id) {
        return trabajadorService.eliminarLogico(id);
    }
    
    @PutMapping("/{id}/cambiar-estado")
    public SuccessResponse<String> cambiarEstado(@PathVariable Integer id) {
		return trabajadorService.cambiarEstado(id);
	}
}
