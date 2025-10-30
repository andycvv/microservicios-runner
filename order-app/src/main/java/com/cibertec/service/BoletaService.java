package com.cibertec.service;

import java.util.List;

import com.cibertec.dto.request.BoletaCreacionDTO;
import com.cibertec.response.BoletaDTO;
import com.cibertec.response.SuccessResponse;

public interface BoletaService {
	public SuccessResponse<List<BoletaDTO>> listarBoletas();
	public SuccessResponse<List<BoletaDTO>> listarBoletasPorTrabajadorId(Integer id);
	public SuccessResponse<List<BoletaDTO>> listarBoletasPorClienteId(Integer id);
	public SuccessResponse<BoletaDTO> obtenerBoletaPorId(Integer id);
	public SuccessResponse<String> crearBoleta(BoletaCreacionDTO creacionDTO);
}
