package com.cibertec.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cibertec.dto.request.CalzadoActualizarDTO;
import com.cibertec.dto.request.CalzadoCreacionDTO;
import com.cibertec.dto.request.CalzadoCreacionDTO;
import com.cibertec.dto.response.CalzadoDTO;
import com.cibertec.dto.response.PaginacionResponse;
import com.cibertec.dto.response.SuccessResponse;
import com.cibertec.entity.Calzado;
import com.cibertec.entity.Color;
import com.cibertec.entity.Producto;
import com.cibertec.entity.Talla;
import com.cibertec.mapper.CalzadoMapper;
import com.cibertec.mapper.PaginacionMapper;
import com.cibertec.repository.ICalzadoRepository;
import com.cibertec.service.CalzadoService;

import jakarta.persistence.NoResultException;

@Service
public class CalzadoServiceImp implements CalzadoService {

    @Autowired
    private ICalzadoRepository calzadoRepo;
    @Autowired
    private CalzadoMapper calzadoMapper;
    @Autowired
    private PaginacionMapper paginacionMapper;

    @Override
    public SuccessResponse<CalzadoDTO> registrar(CalzadoCreacionDTO dto) {
        Calzado c = calzadoMapper.toEntityFromCreateDto(dto);
        Calzado saved = calzadoRepo.save(c);
        return SuccessResponse.ok(calzadoMapper.toDto(saved));
    }

    @Override
    public SuccessResponse<String> eliminarLogico(Integer id) {
        Calzado c = calzadoRepo.findById(id)
        		.orElseThrow(() -> new NoResultException("No se encontro el calzado con id: " + id));
        c.setDelete(true);
        calzadoRepo.save(c);
        return SuccessResponse.ok("Calzado eliminado exitosamente");
    }

    @Override
    public SuccessResponse<PaginacionResponse<CalzadoDTO>> listarTodos(Pageable pageable) {
        Page<CalzadoDTO> page = calzadoRepo.findAll(pageable)
        		.map(calzadoMapper::toDto);
        return SuccessResponse.ok(paginacionMapper.toPaginacionResponse(page));
    }

    @Override
    public SuccessResponse<PaginacionResponse<CalzadoDTO>> listarActivos(Pageable pageable) {
        Page<CalzadoDTO> page = calzadoRepo.findByIsDeleteFalseAndIsEnabledTrue(pageable)
        		.map(calzadoMapper::toDto);
        return SuccessResponse.ok(paginacionMapper.toPaginacionResponse(page));
    }

    @Override
    public SuccessResponse<CalzadoDTO> obtenerPorId(Integer id) {
        Calzado c = calzadoRepo.findById(id)
        		.orElseThrow(() -> new NoResultException("No se encontro el calzado con id: " + id));
        return SuccessResponse.ok(calzadoMapper.toDto(c));
    }

    @Override
    public SuccessResponse<String> cambiarEstado(Integer id) {
        Calzado c = calzadoRepo.findById(id)
        		.orElseThrow(() -> new NoResultException("No se encontro el calzado con id: " + id));
        c.setEnabled(!c.isEnabled());
        calzadoRepo.save(c);
        return SuccessResponse.ok("Estado del calzado actualizado exitosamente");
    }

    @Override
    public SuccessResponse<CalzadoDTO> actualizar(Integer id, CalzadoActualizarDTO dto) {
        Calzado c = calzadoRepo.findById(id)
        		.orElseThrow(() -> new NoResultException("No se encontro el calzado con id: " + id));
        c.setStock(dto.getStock());
        c.setEstado(dto.getEstado());
        if (dto.getIdProducto() != null) 
        	c.setProducto(new Producto(dto.getIdProducto(), null, null, null, null, null, null, null, null, null, null, false, true));
        if (dto.getIdColor() != null) 
        	c.setColor(new Color(dto.getIdColor(), null, null, null, false, true));
        if (dto.getIdTalla() != null) 
        	c.setTalla(new Talla(dto.getIdTalla(), null, null, null, false, true));
        Calzado updated = calzadoRepo.save(c);
        return SuccessResponse.ok(calzadoMapper.toDto(updated));
    }
}
