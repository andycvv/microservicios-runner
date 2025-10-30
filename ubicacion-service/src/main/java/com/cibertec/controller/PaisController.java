package com.cibertec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import com.cibertec.dto.request.PaisActualizarDTO;
import com.cibertec.dto.request.PaisCreacionDTO;
import com.cibertec.dto.response.PaisDTO;
import com.cibertec.dto.response.PaginacionResponse;
import com.cibertec.dto.response.SuccessResponse;
import com.cibertec.service.PaisService;

@RestController
@RequestMapping("/api/paises")
public class PaisController {

    @Autowired
    private PaisService paisService;

    @GetMapping
    public SuccessResponse<PaginacionResponse<PaisDTO>> listarTodos(
            @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return paisService.listarTodos(pageable);
    }

    @GetMapping("/activos")
    public SuccessResponse<PaginacionResponse<PaisDTO>> listarActivos(
            @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return paisService.listarActivos(pageable);
    }

    @GetMapping("/{id}")
    public SuccessResponse<PaisDTO> obtenerPorId(@PathVariable Integer id) {
        return paisService.obtenerPorId(id);
    }

    @PostMapping
    public SuccessResponse<PaisDTO> registrar(@RequestBody PaisCreacionDTO dto) {
        return paisService.registrar(dto);
    }

    @PutMapping("/{id}")
    public SuccessResponse<PaisDTO> actualizar(@PathVariable Integer id, @RequestBody PaisActualizarDTO dto) {
        return paisService.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public SuccessResponse<String> eliminarLogico(@PathVariable Integer id) {
        return paisService.eliminarLogico(id);
    }

    @PutMapping("/{id}/cambiar-estado")
    public SuccessResponse<String> cambiarEstado(@PathVariable Integer id) {
        return paisService.cambiarEstado(id);
    }
}