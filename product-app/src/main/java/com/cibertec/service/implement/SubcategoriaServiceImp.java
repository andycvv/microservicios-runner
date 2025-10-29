package com.cibertec.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cibertec.dto.request.SubcategoriaCreacionDTO;
import com.cibertec.dto.request.SubcategoriaActualizarDTO;
import com.cibertec.dto.response.PaginacionResponse;
import com.cibertec.dto.response.SubcategoriaDTO;
import com.cibertec.dto.response.SuccessResponse;
import com.cibertec.entity.Subcategoria;
import com.cibertec.mapper.PaginacionMapper;
import com.cibertec.mapper.SubcategoriaMapper;
import com.cibertec.repository.ISubcategoriaRepository;
import com.cibertec.service.SubcategoriaService;

import jakarta.persistence.NoResultException;

@Service
public class SubcategoriaServiceImp implements SubcategoriaService {

    @Autowired
    private ISubcategoriaRepository subcategoriaRepo;
    @Autowired
    private SubcategoriaMapper subcategoriaMapper;
    @Autowired
    private PaginacionMapper paginacionMapper;

    @Override
    public SuccessResponse<SubcategoriaDTO> registrar(SubcategoriaCreacionDTO dto) {
        Subcategoria s = subcategoriaMapper.toEntityFromCreateDto(dto);
        Subcategoria saved = subcategoriaRepo.save(s);
        return SuccessResponse.ok(subcategoriaMapper.toDto(saved));
    }

    @Override
    public SuccessResponse<String> eliminarLogico(Integer id) {
        Subcategoria s = subcategoriaRepo.findById(id)
        		.orElseThrow(() -> new NoResultException("No se encontro la subcategoria con id: " + id));
        s.setDelete(true);
        subcategoriaRepo.save(s);
        return SuccessResponse.ok("Subcategoria eliminada exitosamente");
    }

    @Override
    public SuccessResponse<PaginacionResponse<SubcategoriaDTO>> listarTodos(Pageable pageable) {
        Page<SubcategoriaDTO> page = subcategoriaRepo
        		.findAll(pageable)
        		.map(subcategoriaMapper::toDto);
        return SuccessResponse.ok(paginacionMapper.toPaginacionResponse(page));
    }

    @Override
    public SuccessResponse<PaginacionResponse<SubcategoriaDTO>> listarActivos(Pageable pageable) {
        Page<SubcategoriaDTO> page = subcategoriaRepo.findByIsDeleteFalseAndIsEnabledTrue(pageable)
        		.map(subcategoriaMapper::toDto);
        return SuccessResponse.ok(paginacionMapper.toPaginacionResponse(page));
    }

    @Override
    public SuccessResponse<SubcategoriaDTO> obtenerPorId(Integer id) {
        Subcategoria s = subcategoriaRepo.findById(id)
        		.orElseThrow(() -> new NoResultException("No se encontro la subcategoria con id: " + id));
        return SuccessResponse.ok(subcategoriaMapper.toDto(s));
    }

    @Override
    public SuccessResponse<String> cambiarEstado(Integer id) {
        Subcategoria s = subcategoriaRepo.findById(id)
        		.orElseThrow(() -> new NoResultException("No se encontro la subcategoria con id: " + id));
        s.setEnabled(!s.isEnabled());
        subcategoriaRepo.save(s);
        return SuccessResponse.ok("Estado de la subcategoria actualizado exitosamente");
    }

    @Override
    public SuccessResponse<SubcategoriaDTO> actualizar(Integer id, SubcategoriaActualizarDTO dto) {
        Subcategoria s = subcategoriaRepo.findById(id)
        		.orElseThrow(() -> new NoResultException("No se encontro la subcategoria con id: " + id));
        s.setNombre(dto.getNombre());
        if (dto.getIdCategoria() != null) 
        	s.setCategoria(new com.cibertec.entity.Categoria(dto.getIdCategoria(), null, null, null, false, true));
        if (dto.getIsEnabled() != null) 
        	s.setEnabled(dto.getIsEnabled());
        Subcategoria updated = subcategoriaRepo.save(s);
        return SuccessResponse.ok(subcategoriaMapper.toDto(updated));
    }
}
