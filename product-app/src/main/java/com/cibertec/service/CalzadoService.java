package com.cibertec.service;

import org.springframework.data.domain.Pageable;

import com.cibertec.dto.request.CalzadoActualizarDTO;
import com.cibertec.dto.request.CalzadoCreacionDTO;
import com.cibertec.dto.response.CalzadoDTO;
import com.cibertec.dto.response.PaginacionResponse;
import com.cibertec.dto.response.SuccessResponse;

public interface CalzadoService {
    SuccessResponse<CalzadoDTO> registrar(CalzadoCreacionDTO dto);
    SuccessResponse<String> eliminarLogico(Integer id);
    SuccessResponse<PaginacionResponse<CalzadoDTO>> listarTodos(Pageable pageable);
    SuccessResponse<PaginacionResponse<CalzadoDTO>> listarActivos(Pageable pageable);
    SuccessResponse<CalzadoDTO> obtenerPorId(Integer id);
    SuccessResponse<String> cambiarEstado(Integer id);
    SuccessResponse<CalzadoDTO> actualizar(Integer id, CalzadoActualizarDTO dto);
}