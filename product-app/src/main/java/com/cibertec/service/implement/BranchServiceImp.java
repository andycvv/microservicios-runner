package com.cibertec.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cibertec.dto.request.BranchCreacionDTO;
import com.cibertec.dto.request.BranchActualizarDTO;
import com.cibertec.dto.response.BranchDTO;
import com.cibertec.dto.response.PaginacionResponse;
import com.cibertec.dto.response.SuccessResponse;
import com.cibertec.entity.Branch;
import com.cibertec.entity.Subcategoria;
import com.cibertec.mapper.BranchMapper;
import com.cibertec.mapper.PaginacionMapper;
import com.cibertec.repository.IBranchRepository;
import com.cibertec.service.BranchService;

import jakarta.persistence.NoResultException;

@Service
public class BranchServiceImp implements BranchService {

    @Autowired
    private IBranchRepository branchRepo;
    @Autowired
    private BranchMapper branchMapper;
    @Autowired
    private PaginacionMapper paginacionMapper;

    @Override
    public SuccessResponse<BranchDTO> registrar(BranchCreacionDTO dto) {
        Branch b = branchMapper.toEntityFromCreateDto(dto);
        Branch saved = branchRepo.save(b);
        return SuccessResponse.ok(branchMapper.toDto(saved));
    }

    @Override
    public SuccessResponse<String> eliminarLogico(Integer id) {
        Branch b = branchRepo.findById(id).orElseThrow(() -> new NoResultException("No se encontro el branch con id: " + id));
        b.setDelete(true);
        branchRepo.save(b);
        return SuccessResponse.ok("Branch eliminado exitosamente");
    }

    @Override
    public SuccessResponse<PaginacionResponse<BranchDTO>> listarTodos(Pageable pageable) {
        Page<BranchDTO> page = branchRepo.findAll(pageable).map(branchMapper::toDto);
        return SuccessResponse.ok(paginacionMapper.toPaginacionResponse(page));
    }

    @Override
    public SuccessResponse<PaginacionResponse<BranchDTO>> listarActivos(Pageable pageable) {
        Page<BranchDTO> page = branchRepo.findByIsDeleteFalseAndIsEnabledTrue(pageable).map(branchMapper::toDto);
        return SuccessResponse.ok(paginacionMapper.toPaginacionResponse(page));
    }

    @Override
    public SuccessResponse<BranchDTO> obtenerPorId(Integer id) {
        Branch b = branchRepo.findById(id).orElseThrow(() -> new NoResultException("No se encontro el branch con id: " + id));
        return SuccessResponse.ok(branchMapper.toDto(b));
    }

    @Override
    public SuccessResponse<String> cambiarEstado(Integer id) {
        Branch b = branchRepo.findById(id).orElseThrow(() -> new NoResultException("No se encontro el branch con id: " + id));
        b.setEnabled(!b.isEnabled());
        branchRepo.save(b);
        return SuccessResponse.ok("Estado del branch actualizado exitosamente");
    }

    @Override
    public SuccessResponse<BranchDTO> actualizar(Integer id, BranchActualizarDTO dto) {
        Branch b = branchRepo.findById(id).orElseThrow(() -> new NoResultException("No se encontro el branch con id: " + id));
        b.setNombre(dto.getNombre());
        if (dto.getIdSubcategoria() != null) 
        	b.setSubcategoria(new Subcategoria(dto.getIdSubcategoria(), null, null, null, null, false, true));
        if (dto.getIsEnabled() != null) 
        	b.setEnabled(dto.getIsEnabled());
        Branch updated = branchRepo.save(b);
        return SuccessResponse.ok(branchMapper.toDto(updated));
    }
}
