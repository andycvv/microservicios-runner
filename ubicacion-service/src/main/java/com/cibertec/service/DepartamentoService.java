package com.cibertec.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.cibertec.dto.request.DepartamentoActualizarDTO;
import com.cibertec.dto.request.DepartamentoCreacionDTO;
import com.cibertec.dto.response.DepartamentoDTO;
import com.cibertec.dto.response.PaginacionResponse;
import com.cibertec.dto.response.SuccessResponse;

public interface DepartamentoService {
    SuccessResponse<PaginacionResponse<DepartamentoDTO>> listarTodos(Pageable pageable);
    SuccessResponse<PaginacionResponse<DepartamentoDTO>> listarActivos(Pageable pageable);
    SuccessResponse<DepartamentoDTO> obtenerPorId(Integer id);
    SuccessResponse<DepartamentoDTO> registrar(DepartamentoCreacionDTO dto);
    SuccessResponse<DepartamentoDTO> actualizar(Integer id, DepartamentoActualizarDTO dto);
    SuccessResponse<String> eliminarLogico(Integer id);
    SuccessResponse<String> cambiarEstado(Integer id);

    List<DepartamentoDTO> findByPais(Integer paisId);
    List<DepartamentoDTO> findActivosByPais(Integer paisId);
}