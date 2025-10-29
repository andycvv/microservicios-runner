package com.cibertec.service;

import org.springframework.data.domain.Pageable;

import com.cibertec.dto.response.PaginacionResponse;
import com.cibertec.dto.response.MarcaDTO;
import com.cibertec.dto.request.MarcaCreacionDTO;
import com.cibertec.dto.request.MarcaActualizarDTO;
import com.cibertec.dto.response.SuccessResponse;

public interface MarcaService {
    SuccessResponse<MarcaDTO> registrar(MarcaCreacionDTO dto);
    SuccessResponse<String> eliminarLogico(Integer id);
    SuccessResponse<PaginacionResponse<MarcaDTO>> listarTodos(Pageable pageable);
    SuccessResponse<PaginacionResponse<MarcaDTO>> listarActivos(Pageable pageable);
    SuccessResponse<MarcaDTO> obtenerPorId(Integer id);
    SuccessResponse<String> cambiarEstado(Integer id);
    SuccessResponse<MarcaDTO> actualizar(Integer id, MarcaActualizarDTO dto);
}