package com.cibertec.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.entity.Transaccion;
import com.cibertec.response.TransaccionDTO;

@Service
public class TransaccionMapper {

	@Autowired
	private ProductoMapper productoMapper;
	
	public TransaccionDTO toTransaccionDTO (Transaccion transaccion) {
		TransaccionDTO dto = new TransaccionDTO();
		dto.setId(transaccion.getId());
		dto.setProducto(productoMapper.toDto(transaccion.getProducto()));
		dto.setUnidades(transaccion.getUnidades());
		dto.setDescuento(transaccion.getDescuento());
		dto.setPrecioUnitario(transaccion.getPrecioUnitario());
		dto.setTotal(transaccion.getTotal());
		
		return dto;
	}
}
