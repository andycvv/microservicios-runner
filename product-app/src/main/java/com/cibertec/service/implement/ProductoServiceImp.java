package com.cibertec.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cibertec.dto.request.ProductoCreacionDTO;
import com.cibertec.dto.request.ProductoActualizarDTO;
import com.cibertec.dto.response.ProductoDTO;
import com.cibertec.dto.response.PaginacionResponse;
import com.cibertec.dto.response.SuccessResponse;
import com.cibertec.entity.Branch;
import com.cibertec.entity.Categoria;
import com.cibertec.entity.Marca;
import com.cibertec.entity.Material;
import com.cibertec.entity.Producto;
import com.cibertec.entity.Subcategoria;
import com.cibertec.mapper.PaginacionMapper;
import com.cibertec.mapper.ProductoMapper;
import com.cibertec.repository.IProductoRepository;
import com.cibertec.service.ProductoService;

import jakarta.persistence.NoResultException;

@Service
public class ProductoServiceImp implements ProductoService {
    @Autowired
    private IProductoRepository productoRepo;
    @Autowired
    private ProductoMapper productoMapper;
    @Autowired
    private PaginacionMapper paginacionMapper;

    @Override
    public SuccessResponse<ProductoDTO> registrar(ProductoCreacionDTO dto) {
        Producto p = productoMapper.toEntityFromCreateDto(dto);
        Producto saved = productoRepo.save(p);
        return SuccessResponse.ok(productoMapper.toDto(saved));
    }

    @Override
    public SuccessResponse<String> eliminarLogico(Integer id) {
        Producto p = productoRepo.findById(id)
        		.orElseThrow(() -> new NoResultException("No se encontro el producto con id: " + id));
        p.setDelete(true);
        productoRepo.save(p);
        return SuccessResponse.ok("Producto eliminado exitosamente");
    }

    @Override
    public SuccessResponse<PaginacionResponse<ProductoDTO>> listarTodos(Pageable pageable) {
        Page<ProductoDTO> page = productoRepo.findAll(pageable)
        		.map(productoMapper::toDto);
        return SuccessResponse.ok(paginacionMapper.toPaginacionResponse(page));
    }

    @Override
    public SuccessResponse<PaginacionResponse<ProductoDTO>> listarActivos(Pageable pageable) {
        Page<ProductoDTO> page = productoRepo.findByIsDeleteFalseAndIsEnabledTrue(pageable)
        		.map(productoMapper::toDto);
        return SuccessResponse.ok(paginacionMapper.toPaginacionResponse(page));
    }

    @Override
    public SuccessResponse<ProductoDTO> obtenerPorId(Integer id) {
        Producto p = productoRepo.findById(id)
        		.orElseThrow(() -> new NoResultException("No se encontro el producto con id: " + id));
        return SuccessResponse.ok(productoMapper.toDto(p));
    }

    @Override
    public SuccessResponse<String> cambiarEstado(Integer id) {
        Producto p = productoRepo.findById(id)
        		.orElseThrow(() -> new NoResultException("No se encontro el producto con id: " + id));
        p.setEnabled(!p.isEnabled());
        productoRepo.save(p);
        return SuccessResponse.ok("Estado del producto actualizado exitosamente");
    }

    @Override
    public SuccessResponse<ProductoDTO> actualizar(Integer id, ProductoActualizarDTO dto) {
        Producto p = productoRepo.findById(id)
        		.orElseThrow(() -> new NoResultException("No se encontro el producto con id: " + id));
        p.setNombre(dto.getNombre());
        p.setDescripcion(dto.getDescripcion());
        p.setPrecio(dto.getPrecio());
        if (dto.getIdCategoria() != null) {
            Categoria c = new Categoria();
            c.setId(dto.getIdCategoria());
            p.setCategoria(c);
        }
        if (dto.getIdSubcategoria() != null) {
            Subcategoria s = new Subcategoria();
            s.setId(dto.getIdSubcategoria());
            p.setSubcategoria(s);
        }
        if (dto.getIdBranch() != null) {
            Branch b = new Branch();
            b.setId(dto.getIdBranch());
            p.setBranch(b);
        }
        if (dto.getIdMaterial() != null) {
            Material m = new Material();
            m.setId(dto.getIdMaterial());
            p.setMaterial(m);
        }
        if (dto.getIdMarca() != null) {
            Marca ma = new Marca();
            ma.setId(dto.getIdMarca());
            p.setMarca(ma);
        }
        if (dto.getIsEnabled() != null) p.setEnabled(dto.getIsEnabled());
        Producto updated = productoRepo.save(p);
        return SuccessResponse.ok(productoMapper.toDto(updated));
    }
}