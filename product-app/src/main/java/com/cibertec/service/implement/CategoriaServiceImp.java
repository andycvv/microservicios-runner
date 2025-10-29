package com.cibertec.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cibertec.dto.request.CategoriaCreacionDTO;
import com.cibertec.dto.request.CategoriaActualizarDTO;
import com.cibertec.dto.response.CategoriaDTO;
import com.cibertec.dto.response.PaginacionResponse;
import com.cibertec.dto.response.SuccessResponse;
import com.cibertec.entity.Categoria;
import com.cibertec.mapper.CategoriaMapper;
import com.cibertec.mapper.PaginacionMapper;
import com.cibertec.repository.ICategoriaRepository;
import com.cibertec.service.CategoriaService;

import jakarta.persistence.NoResultException;

@Service
public class CategoriaServiceImp implements CategoriaService {

    @Autowired
    private ICategoriaRepository categoriaRepo;
    @Autowired
    private CategoriaMapper categoriaMapper;
    @Autowired
    private PaginacionMapper paginacionMapper;

    @Override
    public SuccessResponse<CategoriaDTO> registrar(CategoriaCreacionDTO dto) {
        Categoria c = new Categoria();
        c.setNombre(dto.getNombre());
        c.setEnabled(true);
        c.setDelete(false);
        Categoria saved = categoriaRepo.save(c);
        return SuccessResponse.ok(categoriaMapper.toDto(saved));
    }

    @Override
    public SuccessResponse<String> eliminarLogico(Integer id) {
        Categoria c = categoriaRepo.findById(id)
        		.orElseThrow(() -> new NoResultException("No se encontro la categoria con id: " + id));
        c.setDelete(true);
        categoriaRepo.save(c);
        return SuccessResponse.ok("Categoria eliminada exitosamente");
    }

    @Override
    public SuccessResponse<PaginacionResponse<CategoriaDTO>> listarTodos(Pageable pageable) {
        Page<CategoriaDTO> page = categoriaRepo
        		.findAll(pageable)
        		.map(categoriaMapper::toDto);
        return SuccessResponse.ok(paginacionMapper.toPaginacionResponse(page));
    }

    @Override
    public SuccessResponse<PaginacionResponse<CategoriaDTO>> listarActivos(Pageable pageable) {
        Page<CategoriaDTO> page = categoriaRepo.findByIsDeleteFalseAndIsEnabledTrue(pageable)
        		.map(categoriaMapper::toDto);
        return SuccessResponse.ok(paginacionMapper.toPaginacionResponse(page));
    }

    @Override
    public SuccessResponse<CategoriaDTO> obtenerPorId(Integer id) {
        Categoria c = categoriaRepo.findById(id)
        		.orElseThrow(() -> new NoResultException("No se encontro la categoria con id: " + id));
        return SuccessResponse.ok(categoriaMapper.toDto(c));
    }

    @Override
    public SuccessResponse<String> cambiarEstado(Integer id) {
        Categoria c = categoriaRepo.findById(id)
        		.orElseThrow(() -> new NoResultException("No se encontro la categoria con id: " + id));
        c.setEnabled(!c.isEnabled());
        categoriaRepo.save(c);
        return SuccessResponse.ok("Estado de la categoria actualizado exitosamente");
    }

    @Override
    public SuccessResponse<CategoriaDTO> actualizar(Integer id, CategoriaActualizarDTO dto) {
        Categoria c = categoriaRepo.findById(id)
        		.orElseThrow(() -> new NoResultException("No se encontro la categoria con id: " + id));
        c.setNombre(dto.getNombre());
        if (dto.getIsEnabled() != null) c.setEnabled(dto.getIsEnabled());
        Categoria updated = categoriaRepo.save(c);
        return SuccessResponse.ok(categoriaMapper.toDto(updated));
    }
}
