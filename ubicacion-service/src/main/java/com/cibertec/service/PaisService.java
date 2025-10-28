package com.cibertec.service;

import java.util.List;

import com.cibertec.dto.response.PaisDTO;

public interface PaisService {
    List<PaisDTO> findAll();
    List<PaisDTO> findActivos();
    PaisDTO findById(Integer id);
    PaisDTO create(PaisDTO pais);
    PaisDTO update(Integer id, PaisDTO pais);
    void delete(Integer id);
}