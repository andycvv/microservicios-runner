package com.cibertec.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cibertec.dto.request.TallaActualizarDTO;
import com.cibertec.dto.request.TallaCreacionDTO;
import com.cibertec.dto.response.PaginacionResponse;
import com.cibertec.dto.response.TallaDTO;
import com.cibertec.dto.response.SuccessResponse;
import com.cibertec.entity.Talla;
import com.cibertec.mapper.PaginacionMapper;
import com.cibertec.mapper.TallaMapper;
import com.cibertec.repository.ITallaRepository;
import com.cibertec.service.TallaService;

import jakarta.persistence.NoResultException;

@Service
public class TallaServiceImp implements TallaService {

    @Autowired
    private ITallaRepository tallaRepo;
    @Autowired
    private TallaMapper tallaMapper;
    @Autowired
    private PaginacionMapper paginacionMapper;

    @Override
    public SuccessResponse<TallaDTO> registrar(TallaCreacionDTO dto) {
        Talla t = tallaMapper.toEntityFromCreateDto(dto);
        Talla saved = tallaRepo.save(t);
        return SuccessResponse.ok(tallaMapper.toDto(saved));
    }

    @Override
    public SuccessResponse<String> eliminarLogico(Integer id) {
        Talla t = tallaRepo.findById(id)
        		.orElseThrow(() -> new NoResultException("No se encontro la talla con id: " + id));
        t.setDelete(true);
        tallaRepo.save(t);
        return SuccessResponse.ok("Talla eliminada exitosamente");
    }

    @Override
    public SuccessResponse<PaginacionResponse<TallaDTO>> listarTodos(Pageable pageable) {
        Page<TallaDTO> page = tallaRepo
        		.findAll(pageable)
        		.map(tallaMapper::toDto);
        return SuccessResponse.ok(paginacionMapper.toPaginacionResponse(page));
    }

    @Override
    public SuccessResponse<PaginacionResponse<TallaDTO>> listarActivos(Pageable pageable) {
        Page<TallaDTO> page = tallaRepo.findByIsDeleteFalseAndIsEnabledTrue(pageable)
        		.map(tallaMapper::toDto);
        return SuccessResponse.ok(paginacionMapper.toPaginacionResponse(page));
    }

    @Override
    public SuccessResponse<TallaDTO> obtenerPorId(Integer id) {
        Talla t = tallaRepo.findById(id)
        		.orElseThrow(() -> new NoResultException("No se encontro la talla con id: " + id));
        return SuccessResponse.ok(tallaMapper.toDto(t));
    }

    @Override
    public SuccessResponse<String> cambiarEstado(Integer id) {
        Talla t = tallaRepo.findById(id)
        		.orElseThrow(() -> new NoResultException("No se encontro la talla con id: " + id));
        t.setEnabled(!t.isEnabled());
        tallaRepo.save(t);
        return SuccessResponse.ok("Estado de la talla actualizado exitosamente");
    }

    @Override
    public SuccessResponse<TallaDTO> actualizar(Integer id, TallaActualizarDTO dto) {
        Talla t = tallaRepo.findById(id)
        		.orElseThrow(() -> new NoResultException("No se encontro la talla con id: " + id));
        t.setNumero(dto.getNumero());
        t.setEnabled(dto.getIsEnabled());
        Talla updated = tallaRepo.save(t);
        return SuccessResponse.ok(tallaMapper.toDto(updated));
    }
}
