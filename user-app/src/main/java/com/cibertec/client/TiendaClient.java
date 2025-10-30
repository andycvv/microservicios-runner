package com.cibertec.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cibertec.dto.response.SuccessResponse;
import com.cibertec.dto.response.TiendaDTO;

@FeignClient(name = "store-app", url = "${spring.feign.store-client.url}")
public interface TiendaClient {
	@GetMapping("/{id}")
	SuccessResponse<TiendaDTO> obtenerTiendaPorId(@PathVariable Integer id);
}
