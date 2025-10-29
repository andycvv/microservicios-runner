package com.cibertec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import com.cibertec.dto.request.CalzadoCreacionDTO;
import com.cibertec.dto.request.CalzadoActualizarDTO;
import com.cibertec.dto.response.CalzadoDTO;
import com.cibertec.dto.response.PaginacionResponse;
import com.cibertec.dto.response.SuccessResponse;
import com.cibertec.service.CalzadoService;

@RestController
@RequestMapping("/api/calzados")
public class CalzadoController {

    @Autowired
    private CalzadoService calzadoService;

    @GetMapping
    public SuccessResponse<PaginacionResponse<CalzadoDTO>> listarTodos(
            @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return calzadoService.listarTodos(pageable);
    }

    @GetMapping("/activos")
    public SuccessResponse<PaginacionResponse<CalzadoDTO>> listarActivos(
            @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return calzadoService.listarActivos(pageable);
    }

    @GetMapping("/{id}")
    public SuccessResponse<CalzadoDTO> obtenerPorId(@PathVariable Integer id) {
        return calzadoService.obtenerPorId(id);
    }

    @PostMapping
    public SuccessResponse<CalzadoDTO> registrar(@RequestBody CalzadoCreacionDTO dto) {
        return calzadoService.registrar(dto);
    }

    @PutMapping("/{id}")
    public SuccessResponse<CalzadoDTO> actualizar(@PathVariable Integer id, @RequestBody CalzadoActualizarDTO dto) {
        return calzadoService.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public SuccessResponse<String> eliminarLogico(@PathVariable Integer id) {
        return calzadoService.eliminarLogico(id);
    }

    @PostMapping("/{id}/cambiar-estado")
    public SuccessResponse<String> cambiarEstado(@PathVariable Integer id) {
        return calzadoService.cambiarEstado(id);
    }
}
