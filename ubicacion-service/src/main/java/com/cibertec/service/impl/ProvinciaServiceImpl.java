package com.cibertec.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.dto.response.ProvinciaDTO;
import com.cibertec.entity.Departamento;
import com.cibertec.entity.Provincia;
import com.cibertec.repository.IProvinciaRepository;
import com.cibertec.repository.IDepartamentoRepository;
import com.cibertec.service.ProvinciaService;

@Service
public class ProvinciaServiceImpl implements ProvinciaService {

    @Autowired
    private IProvinciaRepository provinciaRepository;

    @Autowired
    private IDepartamentoRepository departamentoRepository;

    private ProvinciaDTO toDto(Provincia p) {
        if (p == null) return null;
        ProvinciaDTO dto = new ProvinciaDTO();
        dto.setId(p.getId());
        dto.setNombre(p.getNombre());
        dto.setDepartamentoId(p.getDepartamento() != null ? p.getDepartamento().getId() : null);
        return dto;
    }

    private Provincia toEntity(ProvinciaDTO dto) {
        if (dto == null) return null;
        Provincia p = new Provincia();
        p.setId(dto.getId());
        p.setNombre(dto.getNombre());
        if (dto.getDepartamentoId() != null) {
            Departamento d = departamentoRepository.findById(dto.getDepartamentoId()).orElse(null);
            p.setDepartamento(d);
        }
        return p;
    }

    @Override
    public List<ProvinciaDTO> findAll() {
        return provinciaRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<ProvinciaDTO> findActivos() {
        return provinciaRepository.findAll().stream()
                .filter(p -> !p.isDelete() && p.isEnabled())
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProvinciaDTO findById(Integer id) {
        return provinciaRepository.findById(id).map(this::toDto).orElse(null);
    }

    @Override
    public ProvinciaDTO create(ProvinciaDTO dto) {
        Provincia saved = provinciaRepository.save(toEntity(dto));
        return toDto(saved);
    }

    @Override
    public ProvinciaDTO update(Integer id, ProvinciaDTO dto) {
        return provinciaRepository.findById(id).map(existing -> {
            existing.setNombre(dto.getNombre());
            if (dto.getDepartamentoId() != null) {
                Departamento d = departamentoRepository.findById(dto.getDepartamentoId()).orElse(null);
                existing.setDepartamento(d);
            }
            return toDto(provinciaRepository.save(existing));
        }).orElse(null);
    }

    @Override
    public void delete(Integer id) {
        provinciaRepository.findById(id).ifPresent(p -> {
            p.setDelete(true);
            p.setEnabled(false);
            provinciaRepository.save(p);
        });
    }

    @Override
    public List<ProvinciaDTO> findByDepartamento(Integer departamentoId) {
        return provinciaRepository.findByDepartamento_Id(departamentoId).stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<ProvinciaDTO> findActivosByDepartamento(Integer departamentoId) {
        return provinciaRepository.findByDepartamento_IdAndIsEnabledTrueAndIsDeleteFalse(departamentoId).stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<ProvinciaDTO> findByPaisAndDepartamento(Integer paisId, Integer departamentoId) {
        // repository expects (departamentoId, paisId)
        return provinciaRepository.findByDepartamento_IdAndDepartamento_Pais_Id(departamentoId, paisId).stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<ProvinciaDTO> findActivosByPaisAndDepartamento(Integer paisId, Integer departamentoId) {
        return provinciaRepository.findByDepartamento_IdAndDepartamento_Pais_IdAndIsEnabledTrueAndIsDeleteFalse(departamentoId, paisId).stream().map(this::toDto).collect(Collectors.toList());
    }
}