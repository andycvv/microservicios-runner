package com.cibertec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import com.cibertec.dto.request.DepartamentoActualizarDTO;
import com.cibertec.dto.request.DepartamentoCreacionDTO;
import com.cibertec.dto.response.DepartamentoDTO;
import com.cibertec.dto.response.PaginacionResponse;
import com.cibertec.dto.response.SuccessResponse;
import com.cibertec.service.DepartamentoService;

@RestController
@RequestMapping("/api/departamentos")
public class DepartamentoController {

    @Autowired
    private DepartamentoService departamentoService;

    @GetMapping
    public SuccessResponse<PaginacionResponse<DepartamentoDTO>> listarTodos(
            @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return departamentoService.listarTodos(pageable);
    }

    @GetMapping("/activos")
    public SuccessResponse<PaginacionResponse<DepartamentoDTO>> listarActivos(
            @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return departamentoService.listarActivos(pageable);
    }

    @GetMapping("/{id}")
    public SuccessResponse<DepartamentoDTO> obtenerPorId(@PathVariable Integer id) {
        return departamentoService.obtenerPorId(id);
    }

    @PostMapping
    public SuccessResponse<DepartamentoDTO> registrar(@RequestBody DepartamentoCreacionDTO dto) {
        return departamentoService.registrar(dto);
    }

    @PutMapping("/{id}")
    public SuccessResponse<DepartamentoDTO> actualizar(@PathVariable Integer id, @RequestBody DepartamentoActualizarDTO dto) {
        return departamentoService.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public SuccessResponse<String> eliminarLogico(@PathVariable Integer id) {
        return departamentoService.eliminarLogico(id);
    }

    @PutMapping("/{id}/cambiar-estado")
    public SuccessResponse<String> cambiarEstado(@PathVariable Integer id) {
        return departamentoService.cambiarEstado(id);
    }
}