package com.cibertec.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.client.UbicacionClient;
import com.cibertec.dto.request.TiendaActualizarDTO;
import com.cibertec.dto.request.TiendaCreacionDTO;
import com.cibertec.dto.response.DepartamentoDTO;
import com.cibertec.dto.response.DistritoDTO;
import com.cibertec.dto.response.PaisDTO;
import com.cibertec.dto.response.ProvinciaDTO;
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
	
	@Autowired
	private UbicacionClient ubicacionClient;
	
	@Override
	public SuccessResponse<List<TiendaDTO>> listarTiendas() {
		List<TiendaDTO> lista = tiendaRepository.findAll().stream()
				.map(tiendaMapper::toTiendaDTO)
				.toList();   

		if (lista.isEmpty()) {
			throw new NoResultException("No se encontró ninguna tienda");
		}

		return SuccessResponse.ok(lista);
	}
	
	@Override
	public SuccessResponse<List<TiendaDTO>> listarTiendasActivas() {
		List<TiendaDTO> activos = tiendaRepository.findAll().stream()
				.filter(t -> !t.isDeleted() && t.isEnabled())
				.map(tiendaMapper::toTiendaDTO)
				.toList();
		
		if (activos.isEmpty()) {
			throw new NoResultException("No se encontro ninguna tienda activa");
		}

		return SuccessResponse.ok(activos);
	}

	@Override
	public SuccessResponse<TiendaDTO> obtenerTiendaPorId(Integer id) {
		Tienda tienda = tiendaRepository.findById(id)
				.orElseThrow(() -> new NoResultException("No se encontro la tienda con id: " + id));
		
		return SuccessResponse.ok(tiendaMapper.toTiendaDTO(tienda));
	}

	@Override
	public SuccessResponse<TiendaDTO> crearTienda(TiendaCreacionDTO creacionDTO) {
		SuccessResponse<PaisDTO> paisRes = ubicacionClient.getPais(creacionDTO.getIdPais());
		SuccessResponse<DepartamentoDTO> departamentoRes = ubicacionClient.getDepartamento(creacionDTO.getIdDepartamento());
		SuccessResponse<ProvinciaDTO> provinciaRes = ubicacionClient.getProvincia(creacionDTO.getIdProvincia());
		SuccessResponse<DistritoDTO> distritoRes = ubicacionClient.getDistrito(creacionDTO.getIdDistrito());
		
		Tienda tie = tiendaRepository.save(tiendaMapper.toTienda(creacionDTO));
		
		TiendaDTO dto =  tiendaMapper.toTiendaDTO(tie);
		
		dto.setPais(paisRes.getResponse().getNombre());
		dto.setDepartamento(departamentoRes.getResponse().getNombre());
		dto.setProvincia(provinciaRes.getResponse().getNombre());
		dto.setDistrito(distritoRes.getResponse().getNombre());
		
		return SuccessResponse.ok(dto);
	}

	@Override
	public SuccessResponse<TiendaDTO> actualizarTienda(Integer id, TiendaActualizarDTO actualizarDTO) {
		
		tiendaRepository.findById(id).orElseThrow(() -> new NoResultException("No se encontro la tienda con id: " + id));
	
		SuccessResponse<PaisDTO> paisRes = ubicacionClient.getPais(actualizarDTO.getIdPais());
		SuccessResponse<DepartamentoDTO> departamentoRes = ubicacionClient.getDepartamento(actualizarDTO.getIdDepartamento());
		SuccessResponse<ProvinciaDTO> provinciaRes = ubicacionClient.getProvincia(actualizarDTO.getIdProvincia());
		SuccessResponse<DistritoDTO> distritoRes = ubicacionClient.getDistrito(actualizarDTO.getIdDistrito());
		
		Tienda tie = tiendaRepository.save(tiendaMapper.toTienda(id, actualizarDTO));
		
		TiendaDTO dto =  tiendaMapper.toTiendaDTO(tie);
		
		dto.setPais(paisRes.getResponse().getNombre());
		dto.setDepartamento(departamentoRes.getResponse().getNombre());
		dto.setProvincia(provinciaRes.getResponse().getNombre());
		dto.setDistrito(distritoRes.getResponse().getNombre());
		
		return SuccessResponse.ok(dto);
	}
	
	@Override
	public SuccessResponse<String> cambiarEstado(Integer id) {
		Tienda tienda = tiendaRepository.findById(id)
				.orElseThrow(() -> new NoResultException("No se encontro la tienda con id: " + id));
		
		tienda.setEnabled(!tienda.isEnabled());
		
		tiendaRepository.save(tienda);
		
		return SuccessResponse.ok("Estado de la tienda actualizado correctamente");
	}

	@Override
	public SuccessResponse<String> eliminarTienda(Integer id) {
		Tienda tienda = tiendaRepository.findById(id)
				.orElseThrow(() -> new NoResultException("No se encontro la tienda con id: " + id));
		
		tienda.setDeleted(true);
		
		tiendaRepository.save(tienda);
		
		return SuccessResponse.ok("Se elimino esta tienda correctamente");
	}
	
	
}
