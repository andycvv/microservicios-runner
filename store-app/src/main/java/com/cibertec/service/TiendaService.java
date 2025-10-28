package com.cibertec.service;

import java.util.List;

import com.cibertec.dto.response.SuccessResponse;
import com.cibertec.dto.response.TiendaDTO;

public interface TiendaService {

	public SuccessResponse<List<TiendaDTO>> listarTiendas();
}
