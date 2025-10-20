package com.cibertec.user.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.user.dto.request.TrabajadorDTO;
import com.cibertec.user.service.TrabajadorService;

@RestController
@RequestMapping("/api/trabajador")
public class TrabajadorController {

    @Autowired
    private TrabajadorService trabajadorService;

    // Listados
    @GetMapping
    public Map<String, Object> listarTodos() {
        return trabajadorService.listarTodos();
    }

    @GetMapping("/activos")
    public Map<String, Object> listarActivos() {
        return trabajadorService.listarActivos();
    }

    @GetMapping("/inactivos")
    public Map<String, Object> listarInactivos() {
        return trabajadorService.listarInactivos();
    }

    // Obtener por ID
    @GetMapping("/{id}")
    public Map<String, Object> obtenerPorId(@PathVariable Integer id) {
        return trabajadorService.obtenerPorId(id);
    }

    // Registrar
    @PostMapping
    public Map<String, Object> registrar(@RequestBody TrabajadorDTO trabajador) {
        return trabajadorService.registrar(trabajador);
    }

    // Eliminador lógico
    @DeleteMapping("/{id}")
    public Map<String, Object> eliminarLogico(@PathVariable Integer id) {
        return trabajadorService.eliminarLogico(id);
    }
}
