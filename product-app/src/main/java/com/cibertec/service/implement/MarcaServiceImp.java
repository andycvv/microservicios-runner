package com.cibertec.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cibertec.dto.request.MarcaCreacionDTO;
import com.cibertec.dto.request.MarcaActualizarDTO;
import com.cibertec.dto.response.MarcaDTO;
import com.cibertec.dto.response.PaginacionResponse;
import com.cibertec.dto.response.SuccessResponse;
import com.cibertec.entity.Marca;
import com.cibertec.mapper.MarcaMapper;
import com.cibertec.mapper.PaginacionMapper;
import com.cibertec.repository.IMarcaRepository;
import com.cibertec.service.MarcaService;

import jakarta.persistence.NoResultException;

@Service
public class MarcaServiceImp implements MarcaService {

    @Autowired
    private IMarcaRepository marcaRepo;
    @Autowired
    private MarcaMapper marcaMapper;
    @Autowired
    private PaginacionMapper paginacionMapper;

    @Override
    public SuccessResponse<MarcaDTO> registrar(MarcaCreacionDTO dto) {
        Marca m = new Marca();
        m.setNombre(dto.getNombre());
        m.setEnabled(true);
        m.setDelete(false);
        Marca saved = marcaRepo.save(m);
        return SuccessResponse.ok(marcaMapper.toDto(saved));
    }

    @Override
    public SuccessResponse<String> eliminarLogico(Integer id) {
        Marca m = marcaRepo.findById(id)
        		.orElseThrow(() -> new NoResultException("No se encontro la marca con id: " + id));
        m.setDelete(true);
        marcaRepo.save(m);
        return SuccessResponse.ok("Marca eliminada exitosamente");
    }

    @Override
    public SuccessResponse<PaginacionResponse<MarcaDTO>> listarTodos(Pageable pageable) {
        Page<MarcaDTO> page = marcaRepo
        		.findAll(pageable)
        		.map(marcaMapper::toDto);
        return SuccessResponse.ok(paginacionMapper.toPaginacionResponse(page));
    }

    @Override
    public SuccessResponse<PaginacionResponse<MarcaDTO>> listarActivos(Pageable pageable) {
        Page<MarcaDTO> page = marcaRepo.findByIsDeleteFalseAndIsEnabledTrue(pageable)
        		.map(marcaMapper::toDto);
        return SuccessResponse.ok(paginacionMapper.toPaginacionResponse(page));
    }

    @Override
    public SuccessResponse<MarcaDTO> obtenerPorId(Integer id) {
        Marca m = marcaRepo.findById(id)
        		.orElseThrow(() -> new NoResultException("No se encontro la marca con id: " + id));
        return SuccessResponse.ok(marcaMapper.toDto(m));
    }

    @Override
    public SuccessResponse<String> cambiarEstado(Integer id) {
        Marca m = marcaRepo.findById(id)
        		.orElseThrow(() -> new NoResultException("No se encontro la marca con id: " + id));
        m.setEnabled(!m.isEnabled());
        marcaRepo.save(m);
        return SuccessResponse.ok("Estado de la marca actualizado exitosamente");
    }

    @Override
    public SuccessResponse<MarcaDTO> actualizar(Integer id, MarcaActualizarDTO dto) {
        Marca m = marcaRepo.findById(id)
        		.orElseThrow(() -> new NoResultException("No se encontro la marca con id: " + id));
        m.setNombre(dto.getNombre());
        if (dto.getIsEnabled() != null) m.setEnabled(dto.getIsEnabled());
        Marca updated = marcaRepo.save(m);
        return SuccessResponse.ok(marcaMapper.toDto(updated));
    }
}