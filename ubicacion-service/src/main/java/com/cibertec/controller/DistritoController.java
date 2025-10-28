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

import com.cibertec.dto.response.DistritoDTO;
import com.cibertec.dto.response.SuccessResponse;
import com.cibertec.service.DistritoService;

@RestController
@RequestMapping("/api/distritos")
public class DistritoController {

    @Autowired
    private DistritoService distritoService;

    @GetMapping
    public ResponseEntity<SuccessResponse<List<DistritoDTO>>> listAll() {
        return ResponseEntity.ok(SuccessResponse.ok(distritoService.findAll()));
    }

    @GetMapping("/activos")
    public ResponseEntity<SuccessResponse<List<DistritoDTO>>> listActivos() {
        return ResponseEntity.ok(SuccessResponse.ok(distritoService.findActivos()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse<DistritoDTO>> getById(@PathVariable Integer id) {
        DistritoDTO dto = distritoService.findById(id);
        if (dto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(SuccessResponse.ok(dto));
    }

    @PostMapping
    public ResponseEntity<SuccessResponse<DistritoDTO>> create(@RequestBody DistritoDTO dto) {
        DistritoDTO created = distritoService.create(dto);
        return ResponseEntity.status(201).body(SuccessResponse.created(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponse<DistritoDTO>> update(@PathVariable Integer id, @RequestBody DistritoDTO dto) {
        DistritoDTO updated = distritoService.update(id, dto);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(SuccessResponse.ok(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        distritoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/provincia/{provinciaId}")
    public ResponseEntity<SuccessResponse<List<DistritoDTO>>> listByProvincia(@PathVariable Integer provinciaId) {
        return ResponseEntity.ok(SuccessResponse.ok(distritoService.findByProvincia(provinciaId)));
    }

    @GetMapping("/provincia/{provinciaId}/activos")
    public ResponseEntity<SuccessResponse<List<DistritoDTO>>> listActivosByProvincia(@PathVariable Integer provinciaId) {
        return ResponseEntity.ok(SuccessResponse.ok(distritoService.findActivosByProvincia(provinciaId)));
    }
}
