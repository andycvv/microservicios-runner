package com.cibertec.service;

import org.springframework.data.domain.Pageable;

import com.cibertec.dto.request.TallaActualizarDTO;
import com.cibertec.dto.request.TallaCreacionDTO;
import com.cibertec.dto.response.TallaDTO;
import com.cibertec.dto.response.PaginacionResponse;
import com.cibertec.dto.response.SuccessResponse;

public interface TallaService {
    SuccessResponse<TallaDTO> registrar(TallaCreacionDTO dto);
    SuccessResponse<String> eliminarLogico(Integer id);
    SuccessResponse<PaginacionResponse<TallaDTO>> listarTodos(Pageable pageable);
    SuccessResponse<PaginacionResponse<TallaDTO>> listarActivos(Pageable pageable);
    SuccessResponse<TallaDTO> obtenerPorId(Integer id);
    SuccessResponse<String> cambiarEstado(Integer id);
    SuccessResponse<TallaDTO> actualizar(Integer id, TallaActualizarDTO dto);
}
