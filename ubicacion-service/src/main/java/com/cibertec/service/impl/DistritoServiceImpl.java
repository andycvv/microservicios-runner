package com.cibertec.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cibertec.dto.request.DistritoActualizarDTO;
import com.cibertec.dto.request.DistritoCreacionDTO;
import com.cibertec.dto.response.DistritoDTO;
import com.cibertec.dto.response.PaginacionResponse;
import com.cibertec.dto.response.SuccessResponse;
import com.cibertec.entity.Distrito;
import com.cibertec.entity.Provincia;
import com.cibertec.mapper.PaginacionMapper;
import com.cibertec.repository.IDistritoRepository;
import com.cibertec.repository.IProvinciaRepository;
import com.cibertec.service.DistritoService;

import jakarta.persistence.NoResultException;

@Service
public class DistritoServiceImpl implements DistritoService {

    @Autowired
    private IDistritoRepository distritoRepository;

    @Autowired
    private IProvinciaRepository provinciaRepository;

    @Autowired
    private PaginacionMapper paginacionMapper;

    private DistritoDTO toDto(Distrito d) {
        if (d == null) return null;
        DistritoDTO dto = new DistritoDTO();
        dto.setId(d.getId());
        dto.setNombre(d.getNombre());
        dto.setProvinciaId(d.getProvincia() != null ? d.getProvincia().getId() : null);
        return dto;
    }

    @Override
    public SuccessResponse<PaginacionResponse<DistritoDTO>> listarTodos(Pageable pageable) {
        Page<DistritoDTO> page = distritoRepository.findAll(pageable).map(this::toDto);
        return SuccessResponse.ok(paginacionMapper.toPaginacionResponse(page));
    }

    @Override
    public SuccessResponse<PaginacionResponse<DistritoDTO>> listarActivos(Pageable pageable) {
        Page<DistritoDTO> page = distritoRepository.findByIsEnabledTrueAndIsDeleteFalse(pageable).map(this::toDto);
        return SuccessResponse.ok(paginacionMapper.toPaginacionResponse(page));
    }

    @Override
    public SuccessResponse<DistritoDTO> obtenerPorId(Integer id) {
        Distrito d = distritoRepository.findById(id).orElseThrow(() -> new NoResultException("No se encontro el distrito con id: " + id));
        return SuccessResponse.ok(toDto(d));
    }

    @Override
    public SuccessResponse<DistritoDTO> registrar(DistritoCreacionDTO dto) {
        Distrito d = new Distrito();
        d.setNombre(dto.getNombre());
        if (dto.getProvinciaId() != null) {
            Provincia p = provinciaRepository.findById(dto.getProvinciaId()).orElse(null);
            d.setProvincia(p);
        }
        d.setEnabled(true);
        d.setDelete(false);
        Distrito saved = distritoRepository.save(d);
        return SuccessResponse.ok(toDto(saved));
    }

    @Override
    public SuccessResponse<DistritoDTO> actualizar(Integer id, DistritoActualizarDTO dto) {
        Distrito updated = distritoRepository.findById(id).map(existing -> {
            existing.setNombre(dto.getNombre());
            if (dto.getProvinciaId() != null) {
                Provincia p = provinciaRepository.findById(dto.getProvinciaId()).orElse(null);
                existing.setProvincia(p);
            }
            if (dto.getIsEnabled() != null) existing.setEnabled(dto.getIsEnabled());
            return distritoRepository.save(existing);
        }).orElseThrow(() -> new NoResultException("No se encontro el distrito con id: " + id));
        return SuccessResponse.ok(toDto(updated));
    }

    @Override
    public SuccessResponse<String> eliminarLogico(Integer id) {
        Distrito d = distritoRepository.findById(id).orElseThrow(() -> new NoResultException("No se encontro el distrito con id: " + id));
        d.setDelete(true);
        distritoRepository.save(d);
        return SuccessResponse.ok("Distrito eliminado exitosamente");
    }

    @Override
    public SuccessResponse<String> cambiarEstado(Integer id) {
        Distrito d = distritoRepository.findById(id).orElseThrow(() -> new NoResultException("No se encontro el distrito con id: " + id));
        d.setEnabled(!d.isEnabled());
        distritoRepository.save(d);
        return SuccessResponse.ok("Estado del distrito actualizado exitosamente");
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
