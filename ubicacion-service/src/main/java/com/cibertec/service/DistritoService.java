package com.cibertec.service;

import org.springframework.data.domain.Pageable;

import com.cibertec.dto.request.DistritoActualizarDTO;
import com.cibertec.dto.request.DistritoCreacionDTO;
import com.cibertec.dto.response.DistritoDTO;
import com.cibertec.dto.response.PaginacionResponse;
import com.cibertec.dto.response.SuccessResponse;

public interface DistritoService {
    SuccessResponse<PaginacionResponse<DistritoDTO>> listarTodos(Pageable pageable);
    SuccessResponse<PaginacionResponse<DistritoDTO>> listarActivos(Pageable pageable);
    SuccessResponse<DistritoDTO> obtenerPorId(Integer id);
    SuccessResponse<DistritoDTO> registrar(DistritoCreacionDTO dto);
    SuccessResponse<DistritoDTO> actualizar(Integer id, DistritoActualizarDTO dto);
    SuccessResponse<String> eliminarLogico(Integer id);
    SuccessResponse<String> cambiarEstado(Integer id);

    // legacy list endpoints
    java.util.List<DistritoDTO> findByProvincia(Integer provinciaId);
    java.util.List<DistritoDTO> findActivosByProvincia(Integer provinciaId);

    java.util.List<DistritoDTO> findByPaisDepProv(Integer paisId, Integer departamentoId, Integer provinciaId);
    java.util.List<DistritoDTO> findActivosByPaisDepProv(Integer paisId, Integer departamentoId, Integer provinciaId);
}