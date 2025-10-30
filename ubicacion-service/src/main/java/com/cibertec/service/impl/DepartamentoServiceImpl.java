package com.cibertec.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cibertec.dto.request.DepartamentoActualizarDTO;
import com.cibertec.dto.request.DepartamentoCreacionDTO;
import com.cibertec.dto.response.DepartamentoDTO;
import com.cibertec.dto.response.PaginacionResponse;
import com.cibertec.dto.response.SuccessResponse;
import com.cibertec.entity.Departamento;
import com.cibertec.entity.Pais;
import com.cibertec.mapper.PaginacionMapper;
import com.cibertec.repository.IDepartamentoRepository;
import com.cibertec.repository.IPaisRepository;
import com.cibertec.service.DepartamentoService;

import jakarta.persistence.NoResultException;

@Service
public class DepartamentoServiceImpl implements DepartamentoService {

    @Autowired
    private IDepartamentoRepository departamentoRepository;

    @Autowired
    private IPaisRepository paisRepository;

    @Autowired
    private PaginacionMapper paginacionMapper;

    private DepartamentoDTO toDto(Departamento d) {
        if (d == null) return null;
        DepartamentoDTO dto = new DepartamentoDTO();
        dto.setId(d.getId());
        dto.setNombre(d.getNombre());
        dto.setPaisId(d.getPais() != null ? d.getPais().getId() : null);
        return dto;
    }

    @Override
    public SuccessResponse<PaginacionResponse<DepartamentoDTO>> listarTodos(Pageable pageable) {
        Page<DepartamentoDTO> page = departamentoRepository.findAll(pageable).map(this::toDto);
        return SuccessResponse.ok(paginacionMapper.toPaginacionResponse(page));
    }

    @Override
    public SuccessResponse<PaginacionResponse<DepartamentoDTO>> listarActivos(Pageable pageable) {
        Page<DepartamentoDTO> page = departamentoRepository.findByIsEnabledTrueAndIsDeleteFalse(pageable).map(this::toDto);
        return SuccessResponse.ok(paginacionMapper.toPaginacionResponse(page));
    }

    @Override
    public SuccessResponse<DepartamentoDTO> obtenerPorId(Integer id) {
        Departamento d = departamentoRepository.findById(id).orElseThrow(() -> new NoResultException("No se encontro el departamento con id: " + id));
        return SuccessResponse.ok(toDto(d));
    }

    @Override
    public SuccessResponse<DepartamentoDTO> registrar(DepartamentoCreacionDTO dto) {
        Departamento d = new Departamento();
        d.setNombre(dto.getNombre());
        if (dto.getPaisId() != null) {
            Pais p = paisRepository.findById(dto.getPaisId()).orElse(null);
            d.setPais(p);
        }
        d.setEnabled(true);
        d.setDelete(false);
        Departamento saved = departamentoRepository.save(d);
        return SuccessResponse.ok(toDto(saved));
    }

    @Override
    public SuccessResponse<DepartamentoDTO> actualizar(Integer id, DepartamentoActualizarDTO dto) {
        Departamento updated = departamentoRepository.findById(id).map(existing -> {
            existing.setNombre(dto.getNombre());
            if (dto.getPaisId() != null) {
                Pais p = paisRepository.findById(dto.getPaisId()).orElse(null);
                existing.setPais(p);
            }
            if (dto.getIsEnabled() != null) existing.setEnabled(dto.getIsEnabled());
            return departamentoRepository.save(existing);
        }).orElseThrow(() -> new NoResultException("No se encontro el departamento con id: " + id));
        return SuccessResponse.ok(toDto(updated));
    }

    @Override
    public SuccessResponse<String> eliminarLogico(Integer id) {
        Departamento d = departamentoRepository.findById(id).orElseThrow(() -> new NoResultException("No se encontro el departamento con id: " + id));
        d.setDelete(true);
        departamentoRepository.save(d);
        return SuccessResponse.ok("Departamento eliminado exitosamente");
    }

    @Override
    public SuccessResponse<String> cambiarEstado(Integer id) {
        Departamento d = departamentoRepository.findById(id).orElseThrow(() -> new NoResultException("No se encontro el departamento con id: " + id));
        d.setEnabled(!d.isEnabled());
        departamentoRepository.save(d);
        return SuccessResponse.ok("Estado del departamento actualizado exitosamente");
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