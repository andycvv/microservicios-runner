package com.cibertec.service;

import com.cibertec.dto.request.ProductoCreacionDTO;

import org.springframework.data.domain.Pageable;

import com.cibertec.dto.request.ProductoActualizarDTO;
import com.cibertec.dto.response.PaginacionResponse;
import com.cibertec.dto.response.ProductoDTO;
import com.cibertec.dto.response.SuccessResponse;

public interface ProductoService {
    SuccessResponse<ProductoDTO> registrar(ProductoCreacionDTO dto);
    SuccessResponse<String> eliminarLogico(Integer id);
    SuccessResponse<PaginacionResponse<ProductoDTO>> listarTodos(Pageable pageable);
    SuccessResponse<PaginacionResponse<ProductoDTO>> listarActivos(Pageable pageable);
    SuccessResponse<ProductoDTO> obtenerPorId(Integer id);
    SuccessResponse<String> cambiarEstado(Integer id);
    SuccessResponse<ProductoDTO> actualizar(Integer id, ProductoActualizarDTO dto);
}
