package com.cibertec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import com.cibertec.dto.request.ProvinciaActualizarDTO;
import com.cibertec.dto.request.ProvinciaCreacionDTO;
import com.cibertec.dto.response.ProvinciaDTO;
import com.cibertec.dto.response.PaginacionResponse;
import com.cibertec.dto.response.SuccessResponse;
import com.cibertec.service.ProvinciaService;

@RestController
@RequestMapping("/api/provincias")
public class ProvinciaController {

    @Autowired
    private ProvinciaService provinciaService;

    @GetMapping
    public SuccessResponse<PaginacionResponse<ProvinciaDTO>> listarTodos(
            @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return provinciaService.listarTodos(pageable);
    }

    @GetMapping("/activos")
    public SuccessResponse<PaginacionResponse<ProvinciaDTO>> listarActivos(
            @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return provinciaService.listarActivos(pageable);
    }

    @GetMapping("/{id}")
    public SuccessResponse<ProvinciaDTO> obtenerPorId(@PathVariable Integer id) {
        return provinciaService.obtenerPorId(id);
    }

    @PostMapping
    public SuccessResponse<ProvinciaDTO> registrar(@RequestBody ProvinciaCreacionDTO dto) {
        return provinciaService.registrar(dto);
    }

    @PutMapping("/{id}")
    public SuccessResponse<ProvinciaDTO> actualizar(@PathVariable Integer id, @RequestBody ProvinciaActualizarDTO dto) {
        return provinciaService.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public SuccessResponse<String> eliminarLogico(@PathVariable Integer id) {
        return provinciaService.eliminarLogico(id);
    }

    @PutMapping("/{id}/cambiar-estado")
    public SuccessResponse<String> cambiarEstado(@PathVariable Integer id) {
        return provinciaService.cambiarEstado(id);
    }
}