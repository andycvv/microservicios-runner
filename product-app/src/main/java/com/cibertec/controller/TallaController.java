package com.cibertec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import com.cibertec.dto.request.TallaActualizarDTO;
import com.cibertec.dto.request.TallaCreacionDTO;
import com.cibertec.dto.response.PaginacionResponse;
import com.cibertec.dto.response.TallaDTO;
import com.cibertec.dto.response.SuccessResponse;
import com.cibertec.service.TallaService;

@RestController
@RequestMapping("/api/tallas")
public class TallaController {

    @Autowired
    private TallaService tallaService;

    @GetMapping
    public SuccessResponse<PaginacionResponse<TallaDTO>> listarTodos(
            @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return tallaService.listarTodos(pageable);
    }

    @GetMapping("/activos")
    public SuccessResponse<PaginacionResponse<TallaDTO>> listarActivos(
            @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return tallaService.listarActivos(pageable);
    }

    @GetMapping("/{id}")
    public SuccessResponse<TallaDTO> obtenerPorId(@PathVariable Integer id) {
        return tallaService.obtenerPorId(id);
    }

    @PostMapping
    public SuccessResponse<TallaDTO> registrar(@RequestBody TallaCreacionDTO dto) {
        return tallaService.registrar(dto);
    }

    @PutMapping("/{id}")
    public SuccessResponse<TallaDTO> actualizar(@PathVariable Integer id, @RequestBody TallaActualizarDTO dto) {
        return tallaService.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public SuccessResponse<String> eliminarLogico(@PathVariable Integer id) {
        return tallaService.eliminarLogico(id);
    }

    @PutMapping("/{id}/cambiar-estado")
    public SuccessResponse<String> cambiarEstado(@PathVariable Integer id) {
        return tallaService.cambiarEstado(id);
    }
}
