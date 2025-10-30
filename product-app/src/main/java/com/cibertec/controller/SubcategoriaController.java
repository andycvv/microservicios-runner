package com.cibertec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import com.cibertec.dto.request.SubcategoriaCreacionDTO;
import com.cibertec.dto.request.SubcategoriaActualizarDTO;
import com.cibertec.dto.response.PaginacionResponse;
import com.cibertec.dto.response.SubcategoriaDTO;
import com.cibertec.dto.response.SuccessResponse;
import com.cibertec.service.SubcategoriaService;

@RestController
@RequestMapping("/api/subcategorias")
public class SubcategoriaController {

    @Autowired
    private SubcategoriaService subcategoriaService;

    @GetMapping
    public SuccessResponse<PaginacionResponse<SubcategoriaDTO>> listarTodos(
            @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return subcategoriaService.listarTodos(pageable);
    }

    @GetMapping("/activos")
    public SuccessResponse<PaginacionResponse<SubcategoriaDTO>> listarActivos(
            @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return subcategoriaService.listarActivos(pageable);
    }

    @GetMapping("/{id}")
    public SuccessResponse<SubcategoriaDTO> obtenerPorId(@PathVariable Integer id) {
        return subcategoriaService.obtenerPorId(id);
    }

    @PostMapping
    public SuccessResponse<SubcategoriaDTO> registrar(@RequestBody SubcategoriaCreacionDTO dto) {
        return subcategoriaService.registrar(dto);
    }

    @PutMapping("/{id}")
    public SuccessResponse<SubcategoriaDTO> actualizar(@PathVariable Integer id, @RequestBody SubcategoriaActualizarDTO dto) {
        return subcategoriaService.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public SuccessResponse<String> eliminarLogico(@PathVariable Integer id) {
        return subcategoriaService.eliminarLogico(id);
    }

    @PutMapping("/{id}/cambiar-estado")
    public SuccessResponse<String> cambiarEstado(@PathVariable Integer id) {
        return subcategoriaService.cambiarEstado(id);
    }
}
