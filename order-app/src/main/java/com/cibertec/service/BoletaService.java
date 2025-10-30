package com.cibertec.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cibertec.dto.request.BoletaCreacionDTO;
import com.cibertec.dto.response.PaginacionResponse;
import com.cibertec.response.BoletaDTO;
import com.cibertec.response.SuccessResponse;

public interface BoletaService {
	public SuccessResponse<PaginacionResponse<BoletaDTO>> listarBoletas(Pageable pageable);
	public SuccessResponse<PaginacionResponse<BoletaDTO>> listarBoletasPorTrabajadorId(Integer id, Pageable pageable);
	public SuccessResponse<PaginacionResponse<BoletaDTO>> listarBoletasPorClienteId(Integer id, Pageable pageable);
	public SuccessResponse<BoletaDTO> obtenerBoletaPorId(Integer id);
	public SuccessResponse<String> crearBoleta(BoletaCreacionDTO creacionDTO);
}