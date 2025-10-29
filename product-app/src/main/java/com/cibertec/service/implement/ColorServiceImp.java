package com.cibertec.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cibertec.dto.request.ColorCreacionDTO;
import com.cibertec.dto.request.ColorActualizarDTO;
import com.cibertec.dto.response.ColorDTO;
import com.cibertec.dto.response.PaginacionResponse;
import com.cibertec.dto.response.SuccessResponse;
import com.cibertec.entity.Color;
import com.cibertec.mapper.ColorMapper;
import com.cibertec.mapper.PaginacionMapper;
import com.cibertec.repository.IColorRepository;
import com.cibertec.service.ColorService;

import jakarta.persistence.NoResultException;

@Service
public class ColorServiceImp implements ColorService {

    @Autowired
    private IColorRepository colorRepo;
    @Autowired
    private ColorMapper colorMapper;
    @Autowired
    private PaginacionMapper paginacionMapper;

    @Override
    public SuccessResponse<ColorDTO> registrar(ColorCreacionDTO dto) {
        Color c = colorMapper.toEntityFromCreateDto(dto);
        Color saved = colorRepo.save(c);
        return SuccessResponse.ok(colorMapper.toDto(saved));
    }

    @Override
    public SuccessResponse<String> eliminarLogico(Integer id) {
        Color c = colorRepo.findById(id)
        		.orElseThrow(() -> new NoResultException("No se encontro el color con id: " + id));
        c.setDelete(true);
        colorRepo.save(c);
        return SuccessResponse.ok("Color eliminado exitosamente");
    }

    @Override
    public SuccessResponse<PaginacionResponse<ColorDTO>> listarTodos(Pageable pageable) {
        Page<ColorDTO> page = colorRepo
        		.findAll(pageable)
        		.map(colorMapper::toDto);
        return SuccessResponse.ok(paginacionMapper.toPaginacionResponse(page));
    }

    @Override
    public SuccessResponse<PaginacionResponse<ColorDTO>> listarActivos(Pageable pageable) {
        Page<ColorDTO> page = colorRepo.findByIsDeleteFalseAndIsEnabledTrue(pageable)
        		.map(colorMapper::toDto);
        return SuccessResponse.ok(paginacionMapper.toPaginacionResponse(page));
    }

    @Override
    public SuccessResponse<ColorDTO> obtenerPorId(Integer id) {
        Color c = colorRepo.findById(id)
        		.orElseThrow(() -> new NoResultException("No se encontro el color con id: " + id));
        return SuccessResponse.ok(colorMapper.toDto(c));
    }

    @Override
    public SuccessResponse<String> cambiarEstado(Integer id) {
        Color c = colorRepo.findById(id)
        		.orElseThrow(() -> new NoResultException("No se encontro el color con id: " + id));
        c.setEnabled(!c.isEnabled());
        colorRepo.save(c);
        return SuccessResponse.ok("Estado del color actualizado exitosamente");
    }

    @Override
    public SuccessResponse<ColorDTO> actualizar(Integer id, ColorActualizarDTO dto) {
        Color c = colorRepo.findById(id)
        		.orElseThrow(() -> new NoResultException("No se encontro el color con id: " + id));
        c.setNombre(dto.getNombre());
        if (dto.getIsEnabled() != null) c.setEnabled(dto.getIsEnabled());
        Color updated = colorRepo.save(c);
        return SuccessResponse.ok(colorMapper.toDto(updated));
    }
}
