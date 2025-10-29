package com.cibertec.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cibertec.client.TiendaClient;
import com.cibertec.dto.request.TrabajadorActualizarDTO;
import com.cibertec.dto.request.TrabajadorCreacionDTO;
import com.cibertec.dto.response.PaginacionResponse;
import com.cibertec.dto.response.SuccessResponse;
import com.cibertec.dto.response.TiendaDTO;
import com.cibertec.dto.response.TrabajadorDTO;
import com.cibertec.dto.response.UsuarioDTO;
import com.cibertec.entity.Trabajador;
import com.cibertec.mapper.PaginacionMapper;
import com.cibertec.mapper.TrabajadorMapper;
import com.cibertec.repository.ITrabajadorRepository;
import com.cibertec.service.TrabajadorService;
import com.cibertec.service.UsuarioService;

import jakarta.persistence.NoResultException;

@Service
public class TrabajadorServiceImp implements TrabajadorService {
	@Autowired
	private ITrabajadorRepository trabajadorRepo;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private TiendaClient tiendaClient;
	@Autowired
	private TrabajadorMapper trabajadorMapper;
	@Autowired
	private PaginacionMapper paginacionMapper;

	@Override
	@Transactional
	public SuccessResponse<TrabajadorDTO> registrar(TrabajadorCreacionDTO trabajador) {
		SuccessResponse<TiendaDTO> tiendaRes = tiendaClient.obtenerTiendaPorId(trabajador.getIdTienda());

		SuccessResponse<UsuarioDTO> res = usuarioService.crearUsuario(trabajador.getUsuario());
		Trabajador t = new Trabajador();
		Integer usuarioId = res.getResponse().getId();
		Integer tiendaId = tiendaRes.getResponse().getId();
		t.setIdUsuario(usuarioId);
		t.setIdTienda(tiendaId);
		t.setEnabled(true);
		t.setDelete(false);
		t.setHorasLaborales(trabajador.getHorasLaborales());
		t.setSalario(trabajador.getSalario());

		Trabajador guardado = trabajadorRepo.save(t);
		TrabajadorDTO trabajadorDTO = trabajadorMapper.toDto(guardado);
		return SuccessResponse.ok(trabajadorDTO);
	}

	@Override
	public SuccessResponse<String> eliminarLogico(Integer id) {
		Trabajador trabajador = trabajadorRepo.findById(id)
				.orElseThrow(() -> new NoResultException("No se encontro el trabajador con id: " + id));
		trabajador.setDelete(true);
		trabajadorRepo.save(trabajador);
		return SuccessResponse.ok("Trabajador eliminado exitosamente");
	}

	@Override
	public SuccessResponse<PaginacionResponse<TrabajadorDTO>> listarTodos(Pageable pageable) {
		Page<TrabajadorDTO> page = trabajadorRepo.findAll(pageable).map(trabajadorMapper::toDto);
		return SuccessResponse.ok(paginacionMapper.toPaginacionResponse(page));
	}
	
	@Override
	public SuccessResponse<PaginacionResponse<TrabajadorDTO>> listarActivos(Pageable pageable) {
		Page<TrabajadorDTO> page = trabajadorRepo.findByIsDeleteFalseAndIsEnabledTrue(pageable)
				.map(trabajadorMapper::toDto);
		return SuccessResponse.ok(paginacionMapper.toPaginacionResponse(page));
	}

	@Override
	public SuccessResponse<PaginacionResponse<TrabajadorDTO>> listarInactivos(Pageable pageable) {
		Page<TrabajadorDTO> page = trabajadorRepo.findByIsDeleteTrueOrIsEnabledFalse(pageable)
				.map(trabajadorMapper::toDto);
		return SuccessResponse.ok(paginacionMapper.toPaginacionResponse(page));
	}

	@Override
	public SuccessResponse<TrabajadorDTO> obtenerPorId(Integer id) {
		Trabajador trabajador = trabajadorRepo.findById(id)
				.orElseThrow(() -> new NoResultException("No se encontro el trabajador con id: " + id));
		TrabajadorDTO dto = trabajadorMapper.toDto(trabajador);
		return SuccessResponse.ok(dto);
	}

	@Override
	public SuccessResponse<String> cambiarEstado(Integer id) {
		Trabajador trabajador = trabajadorRepo.findById(id)
				.orElseThrow(() -> new NoResultException("No se encontro el trabajador con id: " + id));
		trabajador.setEnabled(!trabajador.isEnabled());
		trabajadorRepo.save(trabajador);
		return SuccessResponse.ok("Estado del trabajador actualizado exitosamente");
	}

	@Override
	public SuccessResponse<TrabajadorDTO> actualizar(Integer id, TrabajadorActualizarDTO trabajadorDTO) {
		Trabajador trabajador = trabajadorRepo.findById(id)
				.orElseThrow(() -> new NoResultException("No se encontro el trabajador con id: " + id));
		SuccessResponse<TiendaDTO> tiendaRes = tiendaClient.obtenerTiendaPorId(trabajador.getIdTienda());
		
		trabajador.setHorasLaborales(trabajadorDTO.getHorasLaborales());
		trabajador.setSalario(trabajadorDTO.getSalario());
		trabajador.setIdTienda(tiendaRes.getResponse().getId());
		trabajador.setEnabled(trabajadorDTO.getIsEnabled());
		Trabajador actualizado = trabajadorRepo.save(trabajador);
		TrabajadorDTO dto = trabajadorMapper.toDto(actualizado);
		return SuccessResponse.ok(dto);
	}
}