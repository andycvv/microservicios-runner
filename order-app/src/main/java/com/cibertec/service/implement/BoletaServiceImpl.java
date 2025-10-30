package com.cibertec.service.implement;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.cibertec.client.ProductoClient;
import com.cibertec.client.UsuarioClient;
import com.cibertec.dto.request.BoletaCreacionDTO;
import com.cibertec.dto.request.TransaccionCreacionDTO;
import com.cibertec.dto.response.PaginacionResponse;
import com.cibertec.entity.Boleta;
import com.cibertec.entity.Transaccion;
import com.cibertec.mapper.BoletaMapper;
import com.cibertec.mapper.PaginacionMapper;
import com.cibertec.repository.IBoletaRepository;
import com.cibertec.repository.ITransaccionRepository;
import com.cibertec.response.BoletaDTO;
import com.cibertec.response.ProductoDTO;
import com.cibertec.response.SuccessResponse;
import com.cibertec.response.UsuarioDTO;
import com.cibertec.service.BoletaService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;

@Service
public class BoletaServiceImpl implements BoletaService{

	@Autowired
	private IBoletaRepository boletaRepository;
	
	@Autowired
	private BoletaMapper boletaMapper;
	
	@Autowired 
	private ProductoClient productoClient;
	
	@Autowired
	private PaginacionMapper paginacionMapper;
	
	@Autowired
	private UsuarioClient usuarioClient;
	
	@Autowired
	private ITransaccionRepository transaccionRepository;
	
	@Override
	public SuccessResponse<PaginacionResponse<BoletaDTO>> listarBoletas(Pageable pageable) {
		Page<BoletaDTO> boletas = boletaRepository.findAll(pageable)
				.map(boletaMapper::toBoletaDTO);
		if (boletas.isEmpty()) {
			throw new NoResultException("No se encontró ninguna tienda");
		}
		
		return SuccessResponse.ok(paginacionMapper.toPaginacionResponse(boletas));
	}

	@Override
	public SuccessResponse<PaginacionResponse<BoletaDTO>> listarBoletasPorTrabajadorId(Integer id, Pageable pageable) {
		Page<BoletaDTO> boletas = boletaRepository.findByIdTrabajador(id, pageable)
				.map(boletaMapper::toBoletaDTO);
		
		if (boletas.isEmpty()) {
			throw new NoResultException("No se encontró ninguna tienda");
		}
		
		return SuccessResponse.ok(paginacionMapper.toPaginacionResponse(boletas));
	}

	@Override
	public SuccessResponse<PaginacionResponse<BoletaDTO>> listarBoletasPorClienteId(Integer id, Pageable pageable) {
		Page<BoletaDTO> boletas = boletaRepository.findByIdUsuario(id, pageable)
				.map(boletaMapper::toBoletaDTO);
		
		if (boletas.isEmpty()) {
			throw new NoResultException("No se encontró ninguna tienda");
		}
		
		return SuccessResponse.ok(paginacionMapper.toPaginacionResponse(boletas));
	}
	
	@Override
	public SuccessResponse<BoletaDTO> obtenerBoletaPorId(Integer id) {
		BoletaDTO boletaDTO = boletaRepository.findById(id)
				.map(boletaMapper::toBoletaDTO)
				.orElseThrow(() -> new NoResultException("No se encontro la boleta con id: " + id));
		
		return SuccessResponse.ok(boletaDTO);
	}

	@Override
	@Transactional
	@CircuitBreaker(name = "productoService", fallbackMethod = "crearBoletaFallback")
	public SuccessResponse<String> crearBoleta(BoletaCreacionDTO creacionDTO) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		SuccessResponse<UsuarioDTO> usuarioRes = usuarioClient.obtenerUsuarioPorEmail(email);
		
		Boleta boleta = new Boleta();
		boleta.setDireccion(creacionDTO.getDireccion());
		boleta.setTipoTarjeta(creacionDTO.getTipoTarjeta());
		boleta.setTotalDescuento(creacionDTO.getTotalDescuento());
		boleta.setNombreCliente(creacionDTO.getNombreCliente());
		boleta.setNumeroDocumento(creacionDTO.getNumeroDocumento());
		boleta.setSubtotal(creacionDTO.getSubtotal());
		boleta.setIgv(creacionDTO.getIgv());
		boleta.setTotal(creacionDTO.getTotal());
		boleta.setEstado(creacionDTO.getEstado());
		boleta.setObservaciones(creacionDTO.getObservaciones());
		boleta.setIdUsuario(creacionDTO.getIdUsuario());
		boleta.setIdTienda(creacionDTO.getIdTienda());
		boleta.setIdTrabajador(usuarioRes.getResponse().getId());
		
		Boleta boletaGuardada = boletaRepository.save(boleta);

	    List<Transaccion> transacciones = new ArrayList<>();

	    for (TransaccionCreacionDTO transaccionCreacionDTO : creacionDTO.getTransacciones()) {

	        int unidades = transaccionCreacionDTO.getUnidades();

	        SuccessResponse<ProductoDTO> productoRes = productoClient.obtenerPorId(transaccionCreacionDTO.getIdProducto());
	        BigDecimal precio = productoRes.getResponse().getPrecio();

	        BigDecimal total = precio.multiply(BigDecimal.valueOf(unidades));

	        Transaccion transaccion = new Transaccion();
	        transaccion.setUnidades(unidades);
	        transaccion.setPrecioUnitario(precio);
	        transaccion.setTotal(total);
	        transaccion.setIdProducto(transaccionCreacionDTO.getIdProducto());
	        transaccion.setIdBoleta(boletaGuardada.getId());

	        transacciones.add(transaccion);
	    }

	    transaccionRepository.saveAll(transacciones);

	    boletaGuardada.setTransacciones(transacciones);

	    BoletaDTO boletaDTO = boletaMapper.toBoletaDTO(boletaGuardada);
	    return SuccessResponse.ok("Boleta creada con exito");
	}
	
	public SuccessResponse<String> crearBoletaFallback(BoletaCreacionDTO creacionDTO, Throwable t) {
	    return SuccessResponse.ok("La boleta no pudo ser creada debido a un problema interno. Por favor, intente nuevamente más tarde.");
	}

}