package com.cibertec.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cibertec.response.ProductoDTO;
import com.cibertec.response.SuccessResponse;

@FeignClient(name = "product-app", url = "${spring.feign.producto-client.url}")
public interface ProductoClient {

    @GetMapping("/{id}")
    SuccessResponse<ProductoDTO> obtenerPorId(@PathVariable("id") Integer id);
}