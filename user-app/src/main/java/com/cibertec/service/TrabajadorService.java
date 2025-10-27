package com.cibertec.service;

import java.util.Map;

import com.cibertec.dto.request.TrabajadorDTO;


public interface TrabajadorService {

    /// Registrar
    Map<String, Object> registrar(TrabajadorDTO trabajador);


    /// Eliminador Lógico
    Map<String, Object> eliminarLogico(Integer id);

    /// Listados
    /////// Todos
    Map<String, Object> listarTodos();

    /////// Activos
    Map<String, Object> listarActivos();

    /////// Inactivos
    Map<String, Object> listarInactivos();

    /// Obtener por ID
    Map<String, Object> obtenerPorId(Integer id);
}
