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

import com.cibertec.dto.response.PaisDTO;
import com.cibertec.dto.response.SuccessResponse;
import com.cibertec.service.PaisService;

@RestController
@RequestMapping("/api/paises")
public class PaisController {

    @Autowired
    private PaisService paisService;

    @GetMapping
    public ResponseEntity<SuccessResponse<List<PaisDTO>>> listAll() {
        return ResponseEntity.ok(SuccessResponse.ok(paisService.findAll()));
    }

    @GetMapping("/activos")
    public ResponseEntity<SuccessResponse<List<PaisDTO>>> listActivos() {
        return ResponseEntity.ok(SuccessResponse.ok(paisService.findActivos()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse<PaisDTO>> getById(@PathVariable Integer id) {
        PaisDTO dto = paisService.findById(id);
        if (dto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(SuccessResponse.ok(dto));
    }

    @PostMapping
    public ResponseEntity<SuccessResponse<PaisDTO>> create(@RequestBody PaisDTO dto) {
        PaisDTO created = paisService.create(dto);
        return ResponseEntity.status(201).body(SuccessResponse.created(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponse<PaisDTO>> update(@PathVariable Integer id, @RequestBody PaisDTO dto) {
        PaisDTO updated = paisService.update(id, dto);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(SuccessResponse.ok(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        paisService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
