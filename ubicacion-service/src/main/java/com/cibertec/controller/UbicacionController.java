package com.cibertec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.dto.response.DepartamentoDTO;
import com.cibertec.dto.response.DistritoDTO;
import com.cibertec.dto.response.ProvinciaDTO;
import com.cibertec.dto.response.SuccessResponse;
import com.cibertec.service.DepartamentoService;
import com.cibertec.service.DistritoService;
import com.cibertec.service.ProvinciaService;

@RestController
@RequestMapping("/api/paises")
public class UbicacionController {
    @Autowired
    private DepartamentoService departamentoService;

    @Autowired
    private ProvinciaService provinciaService;

    @Autowired
    private DistritoService distritoService;

    @GetMapping("/{paisId}/departamentos")
    public ResponseEntity<SuccessResponse<List<DepartamentoDTO>>> departamentosByPais(@PathVariable Integer paisId) {
        List<DepartamentoDTO> list = departamentoService.findByPais(paisId);
        return ResponseEntity.ok(SuccessResponse.ok(list));
    }

    @GetMapping("/{paisId}/departamentos/activos")
    public ResponseEntity<SuccessResponse<List<DepartamentoDTO>>> departamentosActivosByPais(@PathVariable Integer paisId) {
        List<DepartamentoDTO> list = departamentoService.findActivosByPais(paisId);
        return ResponseEntity.ok(SuccessResponse.ok(list));
    }

    @GetMapping("/{paisId}/departamentos/{depId}/provincias")
    public ResponseEntity<SuccessResponse<List<ProvinciaDTO>>> provinciasByPaisDep(@PathVariable Integer paisId, @PathVariable Integer depId) {
        List<ProvinciaDTO> list = provinciaService.findByPaisAndDepartamento(paisId, depId);
        return ResponseEntity.ok(SuccessResponse.ok(list));
    }

    @GetMapping("/{paisId}/departamentos/{depId}/provincias/activos")
    public ResponseEntity<SuccessResponse<List<ProvinciaDTO>>> provinciasActivosByPaisDep(@PathVariable Integer paisId, @PathVariable Integer depId) {
        List<ProvinciaDTO> list = provinciaService.findActivosByPaisAndDepartamento(paisId, depId);
        return ResponseEntity.ok(SuccessResponse.ok(list));
    }

    @GetMapping("/{paisId}/departamentos/{depId}/provincias/{provId}/distritos")
    public ResponseEntity<SuccessResponse<List<DistritoDTO>>> distritosByPaisDepProv(@PathVariable Integer paisId, @PathVariable Integer depId, @PathVariable Integer provId) {
        List<DistritoDTO> list = distritoService.findByPaisDepProv(paisId, depId, provId);
        return ResponseEntity.ok(SuccessResponse.ok(list));
    }

    @GetMapping("/{paisId}/departamentos/{depId}/provincias/{provId}/distritos/activos")
    public ResponseEntity<SuccessResponse<List<DistritoDTO>>> distritosActivosByPaisDepProv(@PathVariable Integer paisId, @PathVariable Integer depId, @PathVariable Integer provId) {
        List<DistritoDTO> list = distritoService.findActivosByPaisDepProv(paisId, depId, provId);
        return ResponseEntity.ok(SuccessResponse.ok(list));
    }
}