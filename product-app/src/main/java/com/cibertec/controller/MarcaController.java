package com.cibertec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import com.cibertec.dto.request.MarcaCreacionDTO;
import com.cibertec.dto.request.MarcaActualizarDTO;
import com.cibertec.dto.response.MarcaDTO;
import com.cibertec.dto.response.PaginacionResponse;
import com.cibertec.dto.response.SuccessResponse;
import com.cibertec.service.MarcaService;

@RestController
@RequestMapping("/api/marcas")
public class MarcaController {

    @Autowired
    private MarcaService marcaService;

    @GetMapping
    public SuccessResponse<PaginacionResponse<MarcaDTO>> listarTodos(
            @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return marcaService.listarTodos(pageable);
    }

    @GetMapping("/activos")
    public SuccessResponse<PaginacionResponse<MarcaDTO>> listarActivos(
            @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return marcaService.listarActivos(pageable);
    }

    @GetMapping("/{id}")
    public SuccessResponse<MarcaDTO> obtenerPorId(@PathVariable Integer id) {
        return marcaService.obtenerPorId(id);
    }

    @PostMapping
    public SuccessResponse<MarcaDTO> registrar(@RequestBody MarcaCreacionDTO dto) {
        return marcaService.registrar(dto);
    }

    @PutMapping("/{id}")
    public SuccessResponse<MarcaDTO> actualizar(@PathVariable Integer id, @RequestBody MarcaActualizarDTO dto) {
        return marcaService.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public SuccessResponse<String> eliminarLogico(@PathVariable Integer id) {
        return marcaService.eliminarLogico(id);
    }

    @PostMapping("/{id}/cambiar-estado")
    public SuccessResponse<String> cambiarEstado(@PathVariable Integer id) {
        return marcaService.cambiarEstado(id);
    }
}
