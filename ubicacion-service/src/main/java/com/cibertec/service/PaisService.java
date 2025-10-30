package com.cibertec.service;

import org.springframework.data.domain.Pageable;

import com.cibertec.dto.request.PaisActualizarDTO;
import com.cibertec.dto.request.PaisCreacionDTO;
import com.cibertec.dto.response.PaisDTO;
import com.cibertec.dto.response.PaginacionResponse;
import com.cibertec.dto.response.SuccessResponse;

public interface PaisService {
    SuccessResponse<PaginacionResponse<PaisDTO>> listarTodos(Pageable pageable);
    SuccessResponse<PaginacionResponse<PaisDTO>> listarActivos(Pageable pageable);
    SuccessResponse<PaisDTO> obtenerPorId(Integer id);
    SuccessResponse<PaisDTO> registrar(PaisCreacionDTO dto);
    SuccessResponse<PaisDTO> actualizar(Integer id, PaisActualizarDTO dto);
    SuccessResponse<String> eliminarLogico(Integer id);
    SuccessResponse<String> cambiarEstado(Integer id);
}