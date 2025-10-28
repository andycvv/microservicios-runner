package com.cibertec.service;

import java.util.List;

import com.cibertec.dto.response.DepartamentoDTO;

public interface DepartamentoService {
    List<DepartamentoDTO> findAll();
    List<DepartamentoDTO> findActivos();
    DepartamentoDTO findById(Integer id);
    DepartamentoDTO create(DepartamentoDTO dto);
    DepartamentoDTO update(Integer id, DepartamentoDTO dto);
    void delete(Integer id);

    List<DepartamentoDTO> findByPais(Integer paisId);
    List<DepartamentoDTO> findActivosByPais(Integer paisId);
}
