package com.cibertec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import com.cibertec.dto.request.ProductoCreacionDTO;
import com.cibertec.dto.request.ProductoActualizarDTO;
import com.cibertec.dto.response.PaginacionResponse;
import com.cibertec.dto.response.ProductoDTO;
import com.cibertec.dto.response.SuccessResponse;
import com.cibertec.service.ProductoService;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public SuccessResponse<PaginacionResponse<ProductoDTO>> listarTodos(
            @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return productoService.listarTodos(pageable);
    }

    @GetMapping("/activos")
    public SuccessResponse<PaginacionResponse<ProductoDTO>> listarActivos(
            @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return productoService.listarActivos(pageable);
    }

    @GetMapping("/{id}")
    public SuccessResponse<ProductoDTO> obtenerPorId(@PathVariable Integer id) {
        return productoService.obtenerPorId(id);
    }

    @PostMapping
    public SuccessResponse<ProductoDTO> registrar(@RequestBody ProductoCreacionDTO dto) {
        return productoService.registrar(dto);
    }

    @PutMapping("/{id}")
    public SuccessResponse<ProductoDTO> actualizar(@PathVariable Integer id, @RequestBody ProductoActualizarDTO dto) {
        return productoService.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public SuccessResponse<String> eliminarLogico(@PathVariable Integer id) {
        return productoService.eliminarLogico(id);
    }

    @PostMapping("/{id}/cambiar-estado")
    public SuccessResponse<String> cambiarEstado(@PathVariable Integer id) {
        return productoService.cambiarEstado(id);
    }
}
