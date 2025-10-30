package com.cibertec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import com.cibertec.dto.request.ColorCreacionDTO;
import com.cibertec.dto.request.ColorActualizarDTO;
import com.cibertec.dto.response.ColorDTO;
import com.cibertec.dto.response.PaginacionResponse;
import com.cibertec.dto.response.SuccessResponse;
import com.cibertec.service.ColorService;

@RestController
@RequestMapping("/api/colores")
public class ColorController {

    @Autowired
    private ColorService colorService;

    @GetMapping
    public SuccessResponse<PaginacionResponse<ColorDTO>> listarTodos(
            @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return colorService.listarTodos(pageable);
    }

    @GetMapping("/activos")
    public SuccessResponse<PaginacionResponse<ColorDTO>> listarActivos(
            @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return colorService.listarActivos(pageable);
    }

    @GetMapping("/{id}")
    public SuccessResponse<ColorDTO> obtenerPorId(@PathVariable Integer id) {
        return colorService.obtenerPorId(id);
    }

    @PostMapping
    public SuccessResponse<ColorDTO> registrar(@RequestBody ColorCreacionDTO dto) {
        return colorService.registrar(dto);
    }

    @PutMapping("/{id}")
    public SuccessResponse<ColorDTO> actualizar(@PathVariable Integer id, @RequestBody ColorActualizarDTO dto) {
        return colorService.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public SuccessResponse<String> eliminarLogico(@PathVariable Integer id) {
        return colorService.eliminarLogico(id);
    }

    @PutMapping("/{id}/cambiar-estado")
    public SuccessResponse<String> cambiarEstado(@PathVariable Integer id) {
        return colorService.cambiarEstado(id);
    }
}
