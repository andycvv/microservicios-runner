package com.cibertec.service;

import com.cibertec.dto.request.MaterialCreacionDTO;

import org.springframework.data.domain.Pageable;

import com.cibertec.dto.request.MaterialActualizarDTO;
import com.cibertec.dto.response.MaterialDTO;
import com.cibertec.dto.response.PaginacionResponse;
import com.cibertec.dto.response.SuccessResponse;

public interface MaterialService {
    SuccessResponse<MaterialDTO> registrar(MaterialCreacionDTO dto);
    SuccessResponse<String> eliminarLogico(Integer id);
    SuccessResponse<PaginacionResponse<MaterialDTO>> listarTodos(Pageable pageable);
    SuccessResponse<PaginacionResponse<MaterialDTO>> listarActivos(Pageable pageable);
    SuccessResponse<MaterialDTO> obtenerPorId(Integer id);
    SuccessResponse<String> cambiarEstado(Integer id);
    SuccessResponse<MaterialDTO> actualizar(Integer id, MaterialActualizarDTO dto);
}
