package com.cibertec.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cibertec.dto.request.ProvinciaActualizarDTO;
import com.cibertec.dto.request.ProvinciaCreacionDTO;
import com.cibertec.dto.response.ProvinciaDTO;
import com.cibertec.dto.response.PaginacionResponse;
import com.cibertec.dto.response.SuccessResponse;
import com.cibertec.entity.Departamento;
import com.cibertec.entity.Provincia;
import com.cibertec.mapper.PaginacionMapper;
import com.cibertec.repository.IProvinciaRepository;
import com.cibertec.repository.IDepartamentoRepository;
import com.cibertec.service.ProvinciaService;

import jakarta.persistence.NoResultException;

@Service
public class ProvinciaServiceImpl implements ProvinciaService {

    @Autowired
    private IProvinciaRepository provinciaRepository;

    @Autowired
    private IDepartamentoRepository departamentoRepository;

    @Autowired
    private PaginacionMapper paginacionMapper;

    private ProvinciaDTO toDto(Provincia p) {
        if (p == null) return null;
        ProvinciaDTO dto = new ProvinciaDTO();
        dto.setId(p.getId());
        dto.setNombre(p.getNombre());
        dto.setDepartamentoId(p.getDepartamento() != null ? p.getDepartamento().getId() : null);
        return dto;
    }

    @Override
    public SuccessResponse<PaginacionResponse<ProvinciaDTO>> listarTodos(Pageable pageable) {
        Page<ProvinciaDTO> page = provinciaRepository.findAll(pageable).map(this::toDto);
        return SuccessResponse.ok(paginacionMapper.toPaginacionResponse(page));
    }

    @Override
    public SuccessResponse<PaginacionResponse<ProvinciaDTO>> listarActivos(Pageable pageable) {
        Page<ProvinciaDTO> page = provinciaRepository.findByIsEnabledTrueAndIsDeleteFalse(pageable).map(this::toDto);
        return SuccessResponse.ok(paginacionMapper.toPaginacionResponse(page));
    }

    @Override
    public SuccessResponse<ProvinciaDTO> obtenerPorId(Integer id) {
        Provincia p = provinciaRepository.findById(id).orElseThrow(() -> new NoResultException("No se encontro la provincia con id: " + id));
        return SuccessResponse.ok(toDto(p));
    }

    @Override
    public SuccessResponse<ProvinciaDTO> registrar(ProvinciaCreacionDTO dto) {
        Provincia p = new Provincia();
        p.setNombre(dto.getNombre());
        if (dto.getDepartamentoId() != null) {
            Departamento d = departamentoRepository.findById(dto.getDepartamentoId()).orElse(null);
            p.setDepartamento(d);
        }
        p.setEnabled(true);
        p.setDelete(false);
        Provincia saved = provinciaRepository.save(p);
        return SuccessResponse.ok(toDto(saved));
    }

    @Override
    public SuccessResponse<ProvinciaDTO> actualizar(Integer id, ProvinciaActualizarDTO dto) {
        Provincia updated = provinciaRepository.findById(id).map(existing -> {
            existing.setNombre(dto.getNombre());
            if (dto.getDepartamentoId() != null) {
                Departamento d = departamentoRepository.findById(dto.getDepartamentoId()).orElse(null);
                existing.setDepartamento(d);
            }
            if (dto.getIsEnabled() != null) existing.setEnabled(dto.getIsEnabled());
            return provinciaRepository.save(existing);
        }).orElseThrow(() -> new NoResultException("No se encontro la provincia con id: " + id));
        return SuccessResponse.ok(toDto(updated));
    }

    @Override
    public SuccessResponse<String> eliminarLogico(Integer id) {
        Provincia p = provinciaRepository.findById(id).orElseThrow(() -> new NoResultException("No se encontro la provincia con id: " + id));
        p.setDelete(true);
        provinciaRepository.save(p);
        return SuccessResponse.ok("Provincia eliminada exitosamente");
    }

    @Override
    public SuccessResponse<String> cambiarEstado(Integer id) {
        Provincia p = provinciaRepository.findById(id).orElseThrow(() -> new NoResultException("No se encontro la provincia con id: " + id));
        p.setEnabled(!p.isEnabled());
        provinciaRepository.save(p);
        return SuccessResponse.ok("Estado de la provincia actualizado exitosamente");
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
        return provinciaRepository.findByDepartamento_IdAndDepartamento_Pais_Id(departamentoId, paisId).stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<ProvinciaDTO> findActivosByPaisAndDepartamento(Integer paisId, Integer departamentoId) {
        return provinciaRepository.findByDepartamento_IdAndDepartamento_Pais_IdAndIsEnabledTrueAndIsDeleteFalse(departamentoId, paisId).stream().map(this::toDto).collect(Collectors.toList());
    }
}
