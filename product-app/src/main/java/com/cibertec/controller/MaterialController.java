package com.cibertec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import com.cibertec.dto.request.MaterialCreacionDTO;
import com.cibertec.dto.request.MaterialActualizarDTO;
import com.cibertec.dto.response.MaterialDTO;
import com.cibertec.dto.response.PaginacionResponse;
import com.cibertec.dto.response.SuccessResponse;
import com.cibertec.service.MaterialService;

@RestController
@RequestMapping("/api/materiales")
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    @GetMapping
    public SuccessResponse<PaginacionResponse<MaterialDTO>> listarTodos(
            @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return materialService.listarTodos(pageable);
    }

    @GetMapping("/activos")
    public SuccessResponse<PaginacionResponse<MaterialDTO>> listarActivos(
            @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return materialService.listarActivos(pageable);
    }

    @GetMapping("/{id}")
    public SuccessResponse<MaterialDTO> obtenerPorId(@PathVariable Integer id) {
        return materialService.obtenerPorId(id);
    }

    @PostMapping
    public SuccessResponse<MaterialDTO> registrar(@RequestBody MaterialCreacionDTO dto) {
        return materialService.registrar(dto);
    }

    @PutMapping("/{id}")
    public SuccessResponse<MaterialDTO> actualizar(@PathVariable Integer id, @RequestBody MaterialActualizarDTO dto) {
        return materialService.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public SuccessResponse<String> eliminarLogico(@PathVariable Integer id) {
        return materialService.eliminarLogico(id);
    }

    @PutMapping("/{id}/cambiar-estado")
    public SuccessResponse<String> cambiarEstado(@PathVariable Integer id) {
        return materialService.cambiarEstado(id);
    }
}
