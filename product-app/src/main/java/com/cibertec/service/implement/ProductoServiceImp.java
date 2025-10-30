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
import com.cibertec.repository.IBranchRepository;
import com.cibertec.repository.ICategoriaRepository;
import com.cibertec.repository.IMarcaRepository;
import com.cibertec.repository.IMaterialRepository;
import com.cibertec.repository.IProductoRepository;
import com.cibertec.repository.ISubcategoriaRepository;
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
    @Autowired
    private ICategoriaRepository categoriaRepo;
    @Autowired
    private ISubcategoriaRepository subcategoriaRepo;
    @Autowired
    private IBranchRepository branchRepo;
    @Autowired
    private IMaterialRepository materialRepository;
    @Autowired
    private IMarcaRepository marcaRepo;

    @Override
    public SuccessResponse<ProductoDTO> registrar(ProductoCreacionDTO dto) {
    	Categoria cate = categoriaRepo.findById(dto.getIdCategoria())
				.orElseThrow(() -> new NoResultException("No se encontro la categoria con id: " + dto.getIdCategoria()));
    	Subcategoria subcate = subcategoriaRepo.findById(dto.getIdSubcategoria())
    							.orElseThrow(() -> new NoResultException("No se encontro la subcategoria con id: " + dto.getIdSubcategoria()));
    	Branch branch = branchRepo.findById(dto.getIdBranch())
    							.orElseThrow(() -> new NoResultException("No se encontro el branch con id: "+ dto.getIdBranch()));
    
    	Material material = materialRepository.findById(dto.getIdMaterial())
    							.orElseThrow(() -> new NoResultException("No se encontro el material con id: " + dto.getIdMaterial()));
    	
    	Marca marca = marcaRepo.findById(dto.getIdMarca())
    							.orElseThrow(() -> new NoResultException("No se encontro la marca con id: " + dto.getIdMarca()));
    	
        Producto p = productoMapper.toProducto(dto);
        Producto saved = productoRepo.save(p);
        
        ProductoDTO productoDTO = productoMapper.toDto(saved);
        productoDTO.setCategoria(cate.getNombre());
        productoDTO.setSubcategoria(subcate.getNombre());
        productoDTO.setBranch(branch.getNombre());
        productoDTO.setMaterial(material.getNombre());
        productoDTO.setMarca(marca.getNombre());
        
        return SuccessResponse.ok(productoDTO);
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
    	Categoria cate = categoriaRepo.findById(dto.getIdCategoria())
				.orElseThrow(() -> new NoResultException("No se encontro la categoria con id: " + dto.getIdCategoria()));
    	Subcategoria subcate = subcategoriaRepo.findById(dto.getIdSubcategoria())
    							.orElseThrow(() -> new NoResultException("No se encontro la subcategoria con id: " + dto.getIdSubcategoria()));
    	Branch branch = branchRepo.findById(dto.getIdBranch())
    							.orElseThrow(() -> new NoResultException("No se encontro el branch con id: "+ dto.getIdBranch()));
    
    	Material material = materialRepository.findById(dto.getIdMaterial())
    							.orElseThrow(() -> new NoResultException("No se encontro el material con id: " + dto.getIdMaterial()));
    	
    	Marca marca = marcaRepo.findById(dto.getIdMarca())
    							.orElseThrow(() -> new NoResultException("No se encontro la marca con id: " + dto.getIdMarca()));
    	
        Producto p = productoRepo.findById(id)
        		.orElseThrow(() -> new NoResultException("No se encontro el producto con id: " + id));
        
        p.setNombre(dto.getNombre());
        p.setDescripcion(dto.getDescripcion());
        p.setPrecio(dto.getPrecio());
        p.setCategoria(cate);
        p.setSubcategoria(subcate);
        p.setBranch(branch);
        p.setMaterial(material);
        p.setMarca(marca);
        p.setEnabled(dto.getIsEnabled());
        
        Producto updated = productoRepo.save(p);
        ProductoDTO productoDTO = productoMapper.toDto(updated);
        productoDTO.setCategoria(cate.getNombre());
        productoDTO.setSubcategoria(subcate.getNombre());
        productoDTO.setBranch(branch.getNombre());
        productoDTO.setMaterial(material.getNombre());
        productoDTO.setMarca(marca.getNombre());
        return SuccessResponse.ok(productoDTO);
    }
}