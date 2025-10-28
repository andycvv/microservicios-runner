package com.cibertec.service;

import java.util.List;

import com.cibertec.dto.response.ProvinciaDTO;

public interface ProvinciaService {
    List<ProvinciaDTO> findAll();
    List<ProvinciaDTO> findActivos();
    ProvinciaDTO findById(Integer id);
    ProvinciaDTO create(ProvinciaDTO dto);
    ProvinciaDTO update(Integer id, ProvinciaDTO dto);
    void delete(Integer id);

    List<ProvinciaDTO> findByDepartamento(Integer departamentoId);
    List<ProvinciaDTO> findActivosByDepartamento(Integer departamentoId);

    List<ProvinciaDTO> findByPaisAndDepartamento(Integer paisId, Integer departamentoId);
    List<ProvinciaDTO> findActivosByPaisAndDepartamento(Integer paisId, Integer departamentoId);
}
