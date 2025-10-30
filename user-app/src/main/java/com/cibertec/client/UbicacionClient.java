package com.cibertec.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cibertec.dto.response.SuccessResponse;
import com.cibertec.dto.response.PaisDTO;
import com.cibertec.dto.response.DepartamentoDTO;
import com.cibertec.dto.response.ProvinciaDTO;
import com.cibertec.dto.response.DistritoDTO;

@FeignClient(name = "ubicacion-service", url = "${spring.feign.ubicacion-client.url}")
public interface UbicacionClient {

    @GetMapping("/paises/{id}")
    SuccessResponse<PaisDTO> getPais(@PathVariable("id") Integer id);

    @GetMapping("/departamentos/{id}")
    SuccessResponse<DepartamentoDTO> getDepartamento(@PathVariable("id") Integer id);

    @GetMapping("/provincias/{id}")
    SuccessResponse<ProvinciaDTO> getProvincia(@PathVariable("id") Integer id);

    @GetMapping("/distritos/{id}")
    SuccessResponse<DistritoDTO> getDistrito(@PathVariable("id") Integer id);
}