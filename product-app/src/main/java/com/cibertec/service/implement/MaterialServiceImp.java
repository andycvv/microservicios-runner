package com.cibertec.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cibertec.dto.request.MaterialCreacionDTO;
import com.cibertec.dto.request.MaterialActualizarDTO;
import com.cibertec.dto.response.MaterialDTO;
import com.cibertec.dto.response.PaginacionResponse;
import com.cibertec.dto.response.SuccessResponse;
import com.cibertec.entity.Material;
import com.cibertec.mapper.MaterialMapper;
import com.cibertec.mapper.PaginacionMapper;
import com.cibertec.repository.IMaterialRepository;
import com.cibertec.service.MaterialService;

import jakarta.persistence.NoResultException;

@Service
public class MaterialServiceImp implements MaterialService {

    @Autowired
    private IMaterialRepository materialRepo;
    @Autowired
    private MaterialMapper materialMapper;
    @Autowired
    private PaginacionMapper paginacionMapper;

    @Override
    public SuccessResponse<MaterialDTO> registrar(MaterialCreacionDTO dto) {
        Material m = new Material();
        m.setNombre(dto.getNombre());
        m.setEnabled(true);
        m.setDelete(false);
        Material saved = materialRepo.save(m);
        return SuccessResponse.ok(materialMapper.toDto(saved));
    }

    @Override
    public SuccessResponse<String> eliminarLogico(Integer id) {
        Material m = materialRepo.findById(id)
        		.orElseThrow(() -> new NoResultException("No se encontro el material con id: " + id));
        m.setDelete(true);
        materialRepo.save(m);
        return SuccessResponse.ok("Material eliminado exitosamente");
    }

    @Override
    public SuccessResponse<PaginacionResponse<MaterialDTO>> listarTodos(Pageable pageable) {
        Page<MaterialDTO> page = materialRepo
        		.findAll(pageable)
        		.map(materialMapper::toDto);
        return SuccessResponse.ok(paginacionMapper.toPaginacionResponse(page));
    }

    @Override
    public SuccessResponse<PaginacionResponse<MaterialDTO>> listarActivos(Pageable pageable) {
        Page<MaterialDTO> page = materialRepo.findByIsDeleteFalseAndIsEnabledTrue(pageable)
        		.map(materialMapper::toDto);
        return SuccessResponse.ok(paginacionMapper.toPaginacionResponse(page));
    }

    @Override
    public SuccessResponse<MaterialDTO> obtenerPorId(Integer id) {
        Material m = materialRepo.findById(id)
        		.orElseThrow(() -> new NoResultException("No se encontro el material con id: " + id));
        return SuccessResponse.ok(materialMapper.toDto(m));
    }

    @Override
    public SuccessResponse<String> cambiarEstado(Integer id) {
        Material m = materialRepo.findById(id)
        		.orElseThrow(() -> new NoResultException("No se encontro el material con id: " + id));
        m.setEnabled(!m.isEnabled());
        materialRepo.save(m);
        return SuccessResponse.ok("Estado del material actualizado exitosamente");
    }

    @Override
    public SuccessResponse<MaterialDTO> actualizar(Integer id, MaterialActualizarDTO dto) {
        Material m = materialRepo.findById(id)
        		.orElseThrow(() -> new NoResultException("No se encontro el material con id: " + id));
        m.setNombre(dto.getNombre());
        if (dto.getIsEnabled() != null) m.setEnabled(dto.getIsEnabled());
        Material updated = materialRepo.save(m);
        return SuccessResponse.ok(materialMapper.toDto(updated));
    }
}
