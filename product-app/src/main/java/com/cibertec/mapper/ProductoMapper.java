package com.cibertec.mapper;

import org.springframework.stereotype.Service;

import com.cibertec.dto.request.ProductoCreacionDTO;
import com.cibertec.dto.response.ProductoDTO;
import com.cibertec.entity.Categoria;
import com.cibertec.entity.Producto;
import com.cibertec.entity.Subcategoria;
import com.cibertec.entity.Branch;
import com.cibertec.entity.Material;
import com.cibertec.entity.Marca;

@Service
public class ProductoMapper {
    public ProductoDTO toDto(Producto p) {
        if (p == null) return null;
        ProductoDTO dto = new ProductoDTO();
        dto.setId(p.getId());
        dto.setNombre(p.getNombre());
        dto.setDescripcion(p.getDescripcion());
        dto.setPrecio(p.getPrecio());
        dto.setIsEnabled(p.isEnabled());
        if (p.getCategoria() != null) dto.setCategoria(p.getCategoria().getNombre());
        if (p.getSubcategoria() != null) dto.setSubcategoria(p.getSubcategoria().getNombre());
        if (p.getBranch() != null) dto.setBranch(p.getBranch().getNombre());
        if (p.getMaterial() != null) dto.setMaterial(p.getMaterial().getNombre());
        if (p.getMarca() != null) dto.setMarca(p.getMarca().getNombre());
        return dto;
    }

    public Producto toProducto(ProductoCreacionDTO dto) {
        Producto p = new Producto();
        p.setNombre(dto.getNombre());
        p.setDescripcion(dto.getDescripcion());
        p.setPrecio(dto.getPrecio());
        p.setEnabled(true);
        p.setDelete(false);
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
        return p;
    }
}