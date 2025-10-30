package com.cibertec.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.cibertec.dto.request.ProvinciaActualizarDTO;
import com.cibertec.dto.request.ProvinciaCreacionDTO;
import com.cibertec.dto.response.ProvinciaDTO;
import com.cibertec.dto.response.PaginacionResponse;
import com.cibertec.dto.response.SuccessResponse;

public interface ProvinciaService {
    SuccessResponse<PaginacionResponse<ProvinciaDTO>> listarTodos(Pageable pageable);
    SuccessResponse<PaginacionResponse<ProvinciaDTO>> listarActivos(Pageable pageable);
    SuccessResponse<ProvinciaDTO> obtenerPorId(Integer id);
    SuccessResponse<ProvinciaDTO> registrar(ProvinciaCreacionDTO dto);
    SuccessResponse<ProvinciaDTO> actualizar(Integer id, ProvinciaActualizarDTO dto);
    SuccessResponse<String> eliminarLogico(Integer id);
    SuccessResponse<String> cambiarEstado(Integer id);

    List<ProvinciaDTO> findByDepartamento(Integer departamentoId);
    List<ProvinciaDTO> findActivosByDepartamento(Integer departamentoId);

    List<ProvinciaDTO> findByPaisAndDepartamento(Integer paisId, Integer departamentoId);
    List<ProvinciaDTO> findActivosByPaisAndDepartamento(Integer paisId, Integer departamentoId);
}