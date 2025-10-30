package com.cibertec.service.implement;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.client.ProductoClient;
import com.cibertec.dto.request.BoletaCreacionDTO;
import com.cibertec.dto.request.TransaccionCreacionDTO;
import com.cibertec.entity.Boleta;
import com.cibertec.entity.Transaccion;
import com.cibertec.mapper.BoletaMapper;
import com.cibertec.repository.IBoletaRepository;
import com.cibertec.repository.ITransaccionRepository;
import com.cibertec.response.BoletaDTO;
import com.cibertec.response.ProductoDTO;
import com.cibertec.response.SuccessResponse;
import com.cibertec.service.BoletaService;

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
	private ITransaccionRepository transaccionRepository;
	
	@Override
	public SuccessResponse<List<BoletaDTO>> listarBoletas() {
		List<BoletaDTO> lista = boletaRepository.findAll().stream()
				.map(boletaMapper::toBoletaDTO)
				.toList();
		
		if (lista.isEmpty()) {
			throw new NoResultException("No se encontró ninguna tienda");
		}
		
		return SuccessResponse.ok(lista);
	}

	@Override
	public SuccessResponse<List<BoletaDTO>> listarBoletasPorTrabajadorId(Integer id) {
		List<BoletaDTO> boletasPorTrabajadorId = boletaRepository.findByIdTrabajador(id).stream()
				.map(boletaMapper::toBoletaDTO)
				.toList();
		
		if (boletasPorTrabajadorId.isEmpty()) {
			throw new NoResultException("No se encontró ninguna tienda");
		}
		
		return SuccessResponse.ok(boletasPorTrabajadorId);
	}

	@Override
	public SuccessResponse<List<BoletaDTO>> listarBoletasPorClienteId(Integer id) {
		List<BoletaDTO> boletasPorClienteId = boletaRepository.findByIdUsuario(id).stream()
				.map(boletaMapper::toBoletaDTO)
				.toList();
		
		if (boletasPorClienteId.isEmpty()) {
			throw new NoResultException("No se encontró ninguna tienda");
		}
		
		return SuccessResponse.ok(boletasPorClienteId);
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
	public SuccessResponse<BoletaDTO> crearBoleta(BoletaCreacionDTO creacionDTO) {
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
		boleta.setIdTrabajador(creacionDTO.getIdTrabajdor());
		
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
	    return SuccessResponse.ok(boletaDTO);
	}

}
