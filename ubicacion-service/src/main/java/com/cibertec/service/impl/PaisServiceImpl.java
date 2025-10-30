package com.cibertec.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cibertec.dto.request.PaisActualizarDTO;
import com.cibertec.dto.request.PaisCreacionDTO;
import com.cibertec.dto.response.PaisDTO;
import com.cibertec.dto.response.PaginacionResponse;
import com.cibertec.dto.response.SuccessResponse;
import com.cibertec.entity.Pais;
import com.cibertec.mapper.PaginacionMapper;
import com.cibertec.repository.IPaisRepository;
import com.cibertec.service.PaisService;

import jakarta.persistence.NoResultException;

@Service
public class PaisServiceImpl implements PaisService {

    @Autowired
    private IPaisRepository paisRepository;

    @Autowired
    private PaginacionMapper paginacionMapper;

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

    @Override
    public SuccessResponse<PaginacionResponse<PaisDTO>> listarTodos(Pageable pageable) {
        Page<PaisDTO> page = paisRepository.findAll(pageable).map(this::toDto);
        return SuccessResponse.ok(paginacionMapper.toPaginacionResponse(page));
    }

    @Override
    public SuccessResponse<PaginacionResponse<PaisDTO>> listarActivos(Pageable pageable) {
        Page<PaisDTO> page = paisRepository.findByIsEnabledTrueAndIsDeleteFalse(pageable).map(this::toDto);
        return SuccessResponse.ok(paginacionMapper.toPaginacionResponse(page));
    }

    @Override
    public SuccessResponse<PaisDTO> obtenerPorId(Integer id) {
        Pais p = paisRepository.findById(id)
                .orElseThrow(() -> new NoResultException("No se encontro el pais con id: " + id));
        return SuccessResponse.ok(toDto(p));
    }

    @Override
    public SuccessResponse<PaisDTO> registrar(PaisCreacionDTO dto) {
        Pais p = new Pais();
        p.setNombre(dto.getNombre());
        p.setImg(dto.getImg());
        p.setAbreviatura(dto.getAbreviatura());
        p.setSimbolo(dto.getSimbolo());
        p.setEnabled(true);
        p.setDelete(false);
        Pais saved = paisRepository.save(p);
        return SuccessResponse.ok(toDto(saved));
    }

    @Override
    public SuccessResponse<PaisDTO> actualizar(Integer id, PaisActualizarDTO dto) {
        Pais updated = paisRepository.findById(id).map(existing -> {
            existing.setNombre(dto.getNombre());
            existing.setImg(dto.getImg());
            existing.setAbreviatura(dto.getAbreviatura());
            existing.setSimbolo(dto.getSimbolo());
            if (dto.getIsEnabled() != null) existing.setEnabled(dto.getIsEnabled());
            return paisRepository.save(existing);
        }).orElseThrow(() -> new NoResultException("No se encontro el pais con id: " + id));
        return SuccessResponse.ok(toDto(updated));
    }

    @Override
    public SuccessResponse<String> eliminarLogico(Integer id) {
        Pais p = paisRepository.findById(id)
                .orElseThrow(() -> new NoResultException("No se encontro el pais con id: " + id));
        p.setDelete(true);
        paisRepository.save(p);
        return SuccessResponse.ok("Pais eliminado exitosamente");
    }

    @Override
    public SuccessResponse<String> cambiarEstado(Integer id) {
        Pais p = paisRepository.findById(id)
                .orElseThrow(() -> new NoResultException("No se encontro el pais con id: " + id));
        p.setEnabled(!p.isEnabled());
        paisRepository.save(p);
        return SuccessResponse.ok("Estado del pais actualizado exitosamente");
    }

    // keep legacy methods if needed by other modules
    public List<PaisDTO> findActivos() {
        return paisRepository.findByIsEnabledTrueAndIsDeleteFalse().stream().map(this::toDto).collect(Collectors.toList());
    }
}