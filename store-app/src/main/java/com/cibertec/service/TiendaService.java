package com.cibertec.service;

import java.util.List;

import com.cibertec.dto.request.TiendaActualizarDTO;
import com.cibertec.dto.request.TiendaCreacionDTO;
import com.cibertec.dto.response.SuccessResponse;
import com.cibertec.dto.response.TiendaDTO;

public interface TiendaService {

	public SuccessResponse<List<TiendaDTO>> listarTiendas();
	public SuccessResponse<List<TiendaDTO>> listarTiendasActivas();
	public SuccessResponse<TiendaDTO> obtenerTiendaPorId(Integer id);
	public SuccessResponse<TiendaDTO> crearTienda(TiendaCreacionDTO creacionDTO);
	public SuccessResponse<TiendaDTO> actualizarTienda(Integer id, TiendaActualizarDTO actualizarDTO);
	public SuccessResponse<String> cambiarEstado(Integer id);
	public SuccessResponse<String> eliminarTienda(Integer id);
}
