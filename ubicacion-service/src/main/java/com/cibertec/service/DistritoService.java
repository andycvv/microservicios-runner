package com.cibertec.service;

import java.util.List;

import com.cibertec.dto.response.DistritoDTO;

public interface DistritoService {
    List<DistritoDTO> findAll();
    List<DistritoDTO> findActivos();
    DistritoDTO findById(Integer id);
    DistritoDTO create(DistritoDTO dto);
    DistritoDTO update(Integer id, DistritoDTO dto);
    void delete(Integer id);

    List<DistritoDTO> findByProvincia(Integer provinciaId);
    List<DistritoDTO> findActivosByProvincia(Integer provinciaId);

    List<DistritoDTO> findByPaisDepProv(Integer paisId, Integer departamentoId, Integer provinciaId);
    List<DistritoDTO> findActivosByPaisDepProv(Integer paisId, Integer departamentoId, Integer provinciaId);
}
