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

import com.cibertec.dto.response.ProvinciaDTO;
import com.cibertec.dto.response.SuccessResponse;
import com.cibertec.service.ProvinciaService;

@RestController
@RequestMapping("/api/provincias")
public class ProvinciaController {

    @Autowired
    private ProvinciaService provinciaService;

    @GetMapping
    public ResponseEntity<SuccessResponse<List<ProvinciaDTO>>> listAll() {
        return ResponseEntity.ok(SuccessResponse.ok(provinciaService.findAll()));
    }

    @GetMapping("/activos")
    public ResponseEntity<SuccessResponse<List<ProvinciaDTO>>> listActivos() {
        return ResponseEntity.ok(SuccessResponse.ok(provinciaService.findActivos()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse<ProvinciaDTO>> getById(@PathVariable Integer id) {
        ProvinciaDTO dto = provinciaService.findById(id);
        if (dto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(SuccessResponse.ok(dto));
    }

    @PostMapping
    public ResponseEntity<SuccessResponse<ProvinciaDTO>> create(@RequestBody ProvinciaDTO dto) {
        ProvinciaDTO created = provinciaService.create(dto);
        return ResponseEntity.status(201).body(SuccessResponse.created(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponse<ProvinciaDTO>> update(@PathVariable Integer id, @RequestBody ProvinciaDTO dto) {
        ProvinciaDTO updated = provinciaService.update(id, dto);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(SuccessResponse.ok(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        provinciaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/departamento/{departamentoId}")
    public ResponseEntity<SuccessResponse<List<ProvinciaDTO>>> listByDepartamento(@PathVariable Integer departamentoId) {
        return ResponseEntity.ok(SuccessResponse.ok(provinciaService.findByDepartamento(departamentoId)));
    }

    @GetMapping("/departamento/{departamentoId}/activos")
    public ResponseEntity<SuccessResponse<List<ProvinciaDTO>>> listActivosByDepartamento(@PathVariable Integer departamentoId) {
        return ResponseEntity.ok(SuccessResponse.ok(provinciaService.findActivosByDepartamento(departamentoId)));
    }
}
