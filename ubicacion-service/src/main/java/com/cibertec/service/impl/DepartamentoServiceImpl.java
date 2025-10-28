package com.cibertec.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.dto.response.DepartamentoDTO;
import com.cibertec.entity.Departamento;
import com.cibertec.entity.Pais;
import com.cibertec.repository.IDepartamentoRepository;
import com.cibertec.repository.IPaisRepository;
import com.cibertec.service.DepartamentoService;

@Service
public class DepartamentoServiceImpl implements DepartamentoService {

    @Autowired
    private IDepartamentoRepository departamentoRepository;

    @Autowired
    private IPaisRepository paisRepository;

    private DepartamentoDTO toDto(Departamento d) {
        if (d == null) return null;
        DepartamentoDTO dto = new DepartamentoDTO();
        dto.setId(d.getId());
        dto.setNombre(d.getNombre());
        dto.setPaisId(d.getPais() != null ? d.getPais().getId() : null);
        return dto;
    }

    private Departamento toEntity(DepartamentoDTO dto) {
        if (dto == null) return null;
        Departamento d = new Departamento();
        d.setId(dto.getId());
        d.setNombre(dto.getNombre());
        if (dto.getPaisId() != null) {
            Pais p = paisRepository.findById(dto.getPaisId()).orElse(null);
            d.setPais(p);
        }
        return d;
    }

    @Override
    public List<DepartamentoDTO> findAll() {
        return departamentoRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<DepartamentoDTO> findActivos() {
        return departamentoRepository.findAll().stream()
                .filter(d -> !d.isDelete() && d.isEnabled())
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public DepartamentoDTO findById(Integer id) {
        return departamentoRepository.findById(id).map(this::toDto).orElse(null);
    }

    @Override
    public DepartamentoDTO create(DepartamentoDTO dto) {
        Departamento saved = departamentoRepository.save(toEntity(dto));
        return toDto(saved);
    }

    @Override
    public DepartamentoDTO update(Integer id, DepartamentoDTO dto) {
        return departamentoRepository.findById(id).map(existing -> {
            existing.setNombre(dto.getNombre());
            if (dto.getPaisId() != null) {
                Pais p = paisRepository.findById(dto.getPaisId()).orElse(null);
                existing.setPais(p);
            }
            return toDto(departamentoRepository.save(existing));
        }).orElse(null);
    }

    @Override
    public void delete(Integer id) {
        departamentoRepository.findById(id).ifPresent(d -> {
            d.setDelete(true);
            d.setEnabled(false);
            departamentoRepository.save(d);
        });
    }

    @Override
    public List<DepartamentoDTO> findByPais(Integer paisId) {
        return departamentoRepository.findByPais_Id(paisId).stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<DepartamentoDTO> findActivosByPais(Integer paisId) {
        return departamentoRepository.findByPais_IdAndIsEnabledTrueAndIsDeleteFalse(paisId).stream().map(this::toDto).collect(Collectors.toList());
    }
}
