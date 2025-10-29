package com.cibertec.service;

import com.cibertec.dto.request.CategoriaCreacionDTO;

import org.springframework.data.domain.Pageable;

import com.cibertec.dto.request.CategoriaActualizarDTO;
import com.cibertec.dto.response.CategoriaDTO;
import com.cibertec.dto.response.PaginacionResponse;
import com.cibertec.dto.response.SuccessResponse;

public interface CategoriaService {
    SuccessResponse<CategoriaDTO> registrar(CategoriaCreacionDTO dto);
    SuccessResponse<String> eliminarLogico(Integer id);
    SuccessResponse<PaginacionResponse<CategoriaDTO>> listarTodos(Pageable pageable);
    SuccessResponse<PaginacionResponse<CategoriaDTO>> listarActivos(Pageable pageable);
    SuccessResponse<CategoriaDTO> obtenerPorId(Integer id);
    SuccessResponse<String> cambiarEstado(Integer id);
    SuccessResponse<CategoriaDTO> actualizar(Integer id, CategoriaActualizarDTO dto);
}
