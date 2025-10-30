package com.cibertec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import com.cibertec.dto.request.DistritoActualizarDTO;
import com.cibertec.dto.request.DistritoCreacionDTO;
import com.cibertec.dto.response.DistritoDTO;
import com.cibertec.dto.response.PaginacionResponse;
import com.cibertec.dto.response.SuccessResponse;
import com.cibertec.service.DistritoService;

@RestController
@RequestMapping("/api/distritos")
public class DistritoController {

    @Autowired
    private DistritoService distritoService;

    @GetMapping
    public SuccessResponse<PaginacionResponse<DistritoDTO>> listarTodos(
            @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return distritoService.listarTodos(pageable);
    }

    @GetMapping("/activos")
    public SuccessResponse<PaginacionResponse<DistritoDTO>> listarActivos(
            @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return distritoService.listarActivos(pageable);
    }

    @GetMapping("/{id}")
    public SuccessResponse<DistritoDTO> obtenerPorId(@PathVariable Integer id) {
        return distritoService.obtenerPorId(id);
    }

    @PostMapping
    public SuccessResponse<DistritoDTO> registrar(@RequestBody DistritoCreacionDTO dto) {
        return distritoService.registrar(dto);
    }

    @PutMapping("/{id}")
    public SuccessResponse<DistritoDTO> actualizar(@PathVariable Integer id, @RequestBody DistritoActualizarDTO dto) {
        return distritoService.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public SuccessResponse<String> eliminarLogico(@PathVariable Integer id) {
        return distritoService.eliminarLogico(id);
    }

    @PutMapping("/{id}/cambiar-estado")
    public SuccessResponse<String> cambiarEstado(@PathVariable Integer id) {
        return distritoService.cambiarEstado(id);
    }
}