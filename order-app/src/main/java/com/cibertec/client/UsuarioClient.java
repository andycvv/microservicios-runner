package com.cibertec.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cibertec.response.SuccessResponse;
import com.cibertec.response.TrabajadorDTO;
import com.cibertec.response.UsuarioDTO;

@FeignClient(name = "user-app", url = "${spring.feign.user-client.url}")
public interface UsuarioClient {
	@GetMapping("/usuarios/{id}")
	SuccessResponse<UsuarioDTO> obtenerUsuarioPorId(@PathVariable Integer id);
	@GetMapping("/trabajadores/{id}")
	SuccessResponse<TrabajadorDTO> obtenerTrabajadorPorId(@PathVariable Integer id);
	@GetMapping("/email/{email}")
	SuccessResponse<UsuarioDTO> obtenerUsuarioPorEmail(@PathVariable String email);
}
