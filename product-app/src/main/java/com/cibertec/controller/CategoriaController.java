package com.cibertec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import com.cibertec.dto.request.CategoriaCreacionDTO;
import com.cibertec.dto.request.CategoriaActualizarDTO;
import com.cibertec.dto.response.CategoriaDTO;
import com.cibertec.dto.response.PaginacionResponse;
import com.cibertec.dto.response.SuccessResponse;
import com.cibertec.service.CategoriaService;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public SuccessResponse<PaginacionResponse<CategoriaDTO>> listarTodos(
            @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return categoriaService.listarTodos(pageable);
    }

    @GetMapping("/activos")
    public SuccessResponse<PaginacionResponse<CategoriaDTO>> listarActivos(
            @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return categoriaService.listarActivos(pageable);
    }

    @GetMapping("/{id}")
    public SuccessResponse<CategoriaDTO> obtenerPorId(@PathVariable Integer id) {
        return categoriaService.obtenerPorId(id);
    }

    @PostMapping
    public SuccessResponse<CategoriaDTO> registrar(@RequestBody CategoriaCreacionDTO dto) {
        return categoriaService.registrar(dto);
    }

    @PutMapping("/{id}")
    public SuccessResponse<CategoriaDTO> actualizar(@PathVariable Integer id, @RequestBody CategoriaActualizarDTO dto) {
        return categoriaService.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public SuccessResponse<String> eliminarLogico(@PathVariable Integer id) {
        return categoriaService.eliminarLogico(id);
    }

    @PostMapping("/{id}/cambiar-estado")
    public SuccessResponse<String> cambiarEstado(@PathVariable Integer id) {
        return categoriaService.cambiarEstado(id);
    }
}
