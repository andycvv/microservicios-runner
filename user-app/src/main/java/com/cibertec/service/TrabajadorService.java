package com.cibertec.service;

import org.springframework.data.domain.Pageable;

import com.cibertec.dto.request.TrabajadorActualizarDTO;
import com.cibertec.dto.request.TrabajadorCreacionDTO;
import com.cibertec.dto.response.PaginacionResponse;
import com.cibertec.dto.response.SuccessResponse;
import com.cibertec.dto.response.TrabajadorDTO;


public interface TrabajadorService {
    SuccessResponse<PaginacionResponse<TrabajadorDTO>> listarTodos(Pageable pageable);
    SuccessResponse<PaginacionResponse<TrabajadorDTO>> listarActivos(Pageable pageable);
    SuccessResponse<PaginacionResponse<TrabajadorDTO>> listarInactivos(Pageable pageable);
    SuccessResponse<TrabajadorDTO> obtenerPorId(Integer id);
	SuccessResponse<TrabajadorDTO> registrar(TrabajadorCreacionDTO trabajador);
	SuccessResponse<TrabajadorDTO> actualizar(Integer id, TrabajadorActualizarDTO trabajador);
	SuccessResponse<String> eliminarLogico(Integer id);
	SuccessResponse<String> cambiarEstado(Integer id);
}
