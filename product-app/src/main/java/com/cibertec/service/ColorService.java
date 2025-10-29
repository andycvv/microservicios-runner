package com.cibertec.service;

import com.cibertec.dto.request.ColorCreacionDTO;

import org.springframework.data.domain.Pageable;

import com.cibertec.dto.request.ColorActualizarDTO;
import com.cibertec.dto.response.ColorDTO;
import com.cibertec.dto.response.PaginacionResponse;
import com.cibertec.dto.response.SuccessResponse;

public interface ColorService {
    SuccessResponse<ColorDTO> registrar(ColorCreacionDTO dto);
    SuccessResponse<String> eliminarLogico(Integer id);
    SuccessResponse<PaginacionResponse<ColorDTO>> listarTodos(Pageable pageable);
    SuccessResponse<PaginacionResponse<ColorDTO>> listarActivos(Pageable pageable);
    SuccessResponse<ColorDTO> obtenerPorId(Integer id);
    SuccessResponse<String> cambiarEstado(Integer id);
    SuccessResponse<ColorDTO> actualizar(Integer id, ColorActualizarDTO dto);
}
