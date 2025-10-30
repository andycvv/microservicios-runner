package com.cibertec.mapper;

import org.springframework.stereotype.Service;

import com.cibertec.entity.Producto;
import com.cibertec.response.ProductoDTO;


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

}