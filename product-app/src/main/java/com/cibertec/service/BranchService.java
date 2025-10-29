package com.cibertec.service;

import com.cibertec.dto.request.BranchCreacionDTO;

import org.springframework.data.domain.Pageable;

import com.cibertec.dto.request.BranchActualizarDTO;
import com.cibertec.dto.response.BranchDTO;
import com.cibertec.dto.response.PaginacionResponse;
import com.cibertec.dto.response.SuccessResponse;

public interface BranchService {
    SuccessResponse<BranchDTO> registrar(BranchCreacionDTO dto);
    SuccessResponse<String> eliminarLogico(Integer id);
    SuccessResponse<PaginacionResponse<BranchDTO>> listarTodos(Pageable pageable);
    SuccessResponse<PaginacionResponse<BranchDTO>> listarActivos(Pageable pageable);
    SuccessResponse<BranchDTO> obtenerPorId(Integer id);
    SuccessResponse<String> cambiarEstado(Integer id);
    SuccessResponse<BranchDTO> actualizar(Integer id, BranchActualizarDTO dto);
}
