package com.cibertec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.dto.response.DepartamentoDTO;
import com.cibertec.dto.response.SuccessResponse;
import com.cibertec.service.DepartamentoService;

@RestController
@RequestMapping("/api/departamentos")
public class DepartamentoController {

    @Autowired
    private DepartamentoService departamentoService;

    @GetMapping
    public ResponseEntity<SuccessResponse<List<DepartamentoDTO>>> listAll() {
        return ResponseEntity.ok(SuccessResponse.ok(departamentoService.findAll()));
    }

    @GetMapping("/activos")
    public ResponseEntity<SuccessResponse<List<DepartamentoDTO>>> listActivos() {
        return ResponseEntity.ok(SuccessResponse.ok(departamentoService.findActivos()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse<DepartamentoDTO>> getById(@PathVariable Integer id) {
        DepartamentoDTO dto = departamentoService.findById(id);
        if (dto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(SuccessResponse.ok(dto));
    }

    @PostMapping
    public ResponseEntity<SuccessResponse<DepartamentoDTO>> create(@RequestBody DepartamentoDTO dto) {
        DepartamentoDTO created = departamentoService.create(dto);
        return ResponseEntity.status(201).body(SuccessResponse.created(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponse<DepartamentoDTO>> update(@PathVariable Integer id, @RequestBody DepartamentoDTO dto) {
        DepartamentoDTO updated = departamentoService.update(id, dto);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(SuccessResponse.ok(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        departamentoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // hierarchical endpoints
    @GetMapping("/pais/{paisId}")
    public ResponseEntity<SuccessResponse<List<DepartamentoDTO>>> listByPais(@PathVariable Integer paisId) {
        return ResponseEntity.ok(SuccessResponse.ok(departamentoService.findByPais(paisId)));
    }

    @GetMapping("/pais/{paisId}/activos")
    public ResponseEntity<SuccessResponse<List<DepartamentoDTO>>> listActivosByPais(@PathVariable Integer paisId) {
        return ResponseEntity.ok(SuccessResponse.ok(departamentoService.findActivosByPais(paisId)));
    }
}
