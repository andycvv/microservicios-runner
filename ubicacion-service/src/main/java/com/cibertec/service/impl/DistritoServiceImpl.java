package com.cibertec.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.dto.response.DistritoDTO;
import com.cibertec.entity.Distrito;
import com.cibertec.entity.Provincia;
import com.cibertec.repository.IDistritoRepository;
import com.cibertec.repository.IProvinciaRepository;
import com.cibertec.service.DistritoService;

@Service
public class DistritoServiceImpl implements DistritoService {

    @Autowired
    private IDistritoRepository distritoRepository;

    @Autowired
    private IProvinciaRepository provinciaRepository;

    private DistritoDTO toDto(Distrito d) {
        if (d == null) return null;
        DistritoDTO dto = new DistritoDTO();
        dto.setId(d.getId());
        dto.setNombre(d.getNombre());
        dto.setProvinciaId(d.getProvincia() != null ? d.getProvincia().getId() : null);
        return dto;
    }

    private Distrito toEntity(DistritoDTO dto) {
        if (dto == null) return null;
        Distrito d = new Distrito();
        d.setId(dto.getId());
        d.setNombre(dto.getNombre());
        if (dto.getProvinciaId() != null) {
            Provincia p = provinciaRepository.findById(dto.getProvinciaId()).orElse(null);
            d.setProvincia(p);
        }
        return d;
    }

    @Override
    public List<DistritoDTO> findAll() {
        return distritoRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<DistritoDTO> findActivos() {
        return distritoRepository.findAll().stream()
                .filter(d -> !d.isDelete() && d.isEnabled())
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public DistritoDTO findById(Integer id) {
        return distritoRepository.findById(id).map(this::toDto).orElse(null);
    }

    @Override
    public DistritoDTO create(DistritoDTO dto) {
        Distrito saved = distritoRepository.save(toEntity(dto));
        return toDto(saved);
    }

    @Override
    public DistritoDTO update(Integer id, DistritoDTO dto) {
        return distritoRepository.findById(id).map(existing -> {
            existing.setNombre(dto.getNombre());
            if (dto.getProvinciaId() != null) {
                Provincia p = provinciaRepository.findById(dto.getProvinciaId()).orElse(null);
                existing.setProvincia(p);
            }
            return toDto(distritoRepository.save(existing));
        }).orElse(null);
    }

    @Override
    public void delete(Integer id) {
        distritoRepository.findById(id).ifPresent(d -> {
            d.setDelete(true);
            d.setEnabled(false);
            distritoRepository.save(d);
        });
    }

    @Override
    public List<DistritoDTO> findByProvincia(Integer provinciaId) {
        return distritoRepository.findByProvincia_Id(provinciaId).stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<DistritoDTO> findActivosByProvincia(Integer provinciaId) {
        return distritoRepository.findByProvincia_IdAndIsEnabledTrueAndIsDeleteFalse(provinciaId).stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<DistritoDTO> findByPaisDepProv(Integer paisId, Integer departamentoId, Integer provinciaId) {
        return distritoRepository.findByProvincia_IdAndProvincia_Departamento_IdAndProvincia_Departamento_Pais_Id(provinciaId, departamentoId, paisId).stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<DistritoDTO> findActivosByPaisDepProv(Integer paisId, Integer departamentoId, Integer provinciaId) {
        return distritoRepository.findByProvincia_IdAndProvincia_Departamento_IdAndProvincia_Departamento_Pais_IdAndIsEnabledTrueAndIsDeleteFalse(provinciaId, departamentoId, paisId).stream().map(this::toDto).collect(Collectors.toList());
    }
}
