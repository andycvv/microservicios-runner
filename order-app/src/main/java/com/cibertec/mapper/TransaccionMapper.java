package com.cibertec.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.client.ProductoClient;
import com.cibertec.entity.Transaccion;
import com.cibertec.response.ProductoDTO;
import com.cibertec.response.SuccessResponse;
import com.cibertec.response.TransaccionDTO;

@Service
public class TransaccionMapper {
	@Autowired
	private ProductoClient productoClient;
	
	public TransaccionDTO toTransaccionDTO (Transaccion transaccion) {
		SuccessResponse<ProductoDTO> productoResponse = productoClient.obtenerPorId(transaccion.getIdProducto());
		
		TransaccionDTO dto = new TransaccionDTO();
		dto.setId(transaccion.getId());
		dto.setProducto(productoResponse.getResponse());
		dto.setUnidades(transaccion.getUnidades());
		dto.setDescuento(transaccion.getDescuento());
		dto.setPrecioUnitario(transaccion.getPrecioUnitario());
		dto.setTotal(transaccion.getTotal());
		
		return dto;
	}
}
