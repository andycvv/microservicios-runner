package com.cibertec.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.dto.response.SuccessResponse;
import com.cibertec.dto.response.TiendaDTO;
import com.cibertec.entity.Tienda;
import com.cibertec.mapper.TiendaMapper;
import com.cibertec.repository.ITiendaRepository;
import com.cibertec.service.TiendaService;

import jakarta.persistence.NoResultException;

@Service
public class TiendaServiceImpl implements TiendaService {

	@Autowired
    private ITiendaRepository tiendaRepository;
	
	@Autowired
    private TiendaMapper tiendaMapper;
	
	@Override
	public SuccessResponse<List<TiendaDTO>> listarTiendas() {
		List<TiendaDTO> list = tiendaRepository.findAll().stream()
				.map(tiendaMapper::toTiendaDTO)
				.toList();   

		if (list.isEmpty()) {
			throw new NoResultException("No se encontró ninguna tienda");
		}

		return SuccessResponse.ok(list);
	}

	@Override
	public SuccessResponse<TiendaDTO> obtenerTiendaPorId(Integer id) {
		Tienda tienda = tiendaRepository.findById(id)
				.orElseThrow(() -> new NoResultException("No se encontro la tienda con id: " + id));
		
		return SuccessResponse.ok(tiendaMapper.toTiendaDTO(tienda));
	}
	
	
}
