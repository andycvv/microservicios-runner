package com.cibertec.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.dto.response.PaisDTO;
import com.cibertec.dto.response.SuccessResponse;
import com.cibertec.entity.Pais;
import com.cibertec.repository.IPaisRepository;
import com.cibertec.service.PaisService;

@Service
public class PaisServiceImpl implements PaisService {

    @Autowired
    private IPaisRepository paisRepository;

    private PaisDTO toDto(Pais p) {
        if (p == null) return null;
        PaisDTO dto = new PaisDTO();
        dto.setId(p.getId());
        dto.setNombre(p.getNombre());
        dto.setImg(p.getImg());
        dto.setAbreviatura(p.getAbreviatura());
        dto.setSimbolo(p.getSimbolo());
        return dto;
    }

    private Pais toEntity(PaisDTO dto) {
        if (dto == null) return null;
        Pais p = new Pais();
        p.setId(dto.getId());
        p.setNombre(dto.getNombre());
        p.setImg(dto.getImg());
        p.setAbreviatura(dto.getAbreviatura());
        p.setSimbolo(dto.getSimbolo());
        return p;
    }

    @Override
    public List<PaisDTO> findAll() {
        return paisRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public PaisDTO findById(Integer id) {
        return paisRepository.findById(id).map(this::toDto).orElse(null);
    }

    @Override
    public PaisDTO create(PaisDTO paisDto) {
        Pais saved = paisRepository.save(toEntity(paisDto));
        return toDto(saved);
    }

    @Override
    public PaisDTO update(Integer id, PaisDTO paisDto) {
        return paisRepository.findById(id).map(existing -> {
            existing.setNombre(paisDto.getNombre());
            existing.setImg(paisDto.getImg());
            existing.setAbreviatura(paisDto.getAbreviatura());
            existing.setSimbolo(paisDto.getSimbolo());
            return toDto(paisRepository.save(existing));
        }).orElse(null);
    }

    @Override
    public void delete(Integer id) {
        paisRepository.findById(id).ifPresent(p -> {
            p.setDelete(true);
            p.setEnabled(false);
            paisRepository.save(p);
        });
    }

    public List<PaisDTO> findActivos() {
        return paisRepository.findByIsEnabledTrueAndIsDeleteFalse().stream().map(this::toDto).collect(Collectors.toList());
    }
}
