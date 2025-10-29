package com.cibertec.service;

import com.cibertec.dto.request.SubcategoriaCreacionDTO;

import org.springframework.data.domain.Pageable;

import com.cibertec.dto.request.SubcategoriaActualizarDTO;
import com.cibertec.dto.response.PaginacionResponse;
import com.cibertec.dto.response.SubcategoriaDTO;
import com.cibertec.dto.response.SuccessResponse;

public interface SubcategoriaService {
    SuccessResponse<SubcategoriaDTO> registrar(SubcategoriaCreacionDTO dto);
    SuccessResponse<String> eliminarLogico(Integer id);
    SuccessResponse<PaginacionResponse<SubcategoriaDTO>> listarTodos(Pageable pageable);
    SuccessResponse<PaginacionResponse<SubcategoriaDTO>> listarActivos(Pageable pageable);
    SuccessResponse<SubcategoriaDTO> obtenerPorId(Integer id);
    SuccessResponse<String> cambiarEstado(Integer id);
    SuccessResponse<SubcategoriaDTO> actualizar(Integer id, SubcategoriaActualizarDTO dto);
}
