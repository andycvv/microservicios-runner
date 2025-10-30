package com.cibertec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import com.cibertec.dto.request.BranchCreacionDTO;
import com.cibertec.dto.request.BranchActualizarDTO;
import com.cibertec.dto.response.BranchDTO;
import com.cibertec.dto.response.PaginacionResponse;
import com.cibertec.dto.response.SuccessResponse;
import com.cibertec.service.BranchService;

@RestController
@RequestMapping("/api/branches")
public class BranchController {

    @Autowired
    private BranchService branchService;

    @GetMapping
    public SuccessResponse<PaginacionResponse<BranchDTO>> listarTodos(
            @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return branchService.listarTodos(pageable);
    }

    @GetMapping("/activos")
    public SuccessResponse<PaginacionResponse<BranchDTO>> listarActivos(
            @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return branchService.listarActivos(pageable);
    }

    @GetMapping("/{id}")
    public SuccessResponse<BranchDTO> obtenerPorId(@PathVariable Integer id) {
        return branchService.obtenerPorId(id);
    }

    @PostMapping
    public SuccessResponse<BranchDTO> registrar(@RequestBody BranchCreacionDTO dto) {
        return branchService.registrar(dto);
    }

    @PutMapping("/{id}")
    public SuccessResponse<BranchDTO> actualizar(@PathVariable Integer id, @RequestBody BranchActualizarDTO dto) {
        return branchService.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public SuccessResponse<String> eliminarLogico(@PathVariable Integer id) {
        return branchService.eliminarLogico(id);
    }

    @PutMapping("/{id}/cambiar-estado")
    public SuccessResponse<String> cambiarEstado(@PathVariable Integer id) {
        return branchService.cambiarEstado(id);
    }
}
