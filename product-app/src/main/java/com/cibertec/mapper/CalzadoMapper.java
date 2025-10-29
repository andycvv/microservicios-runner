package com.cibertec.mapper;

import org.springframework.stereotype.Service;

import com.cibertec.dto.response.CalzadoDTO;
import com.cibertec.entity.Calzado;

@Service
public class CalzadoMapper {
    public CalzadoDTO toDto(Calzado c) {
        if (c == null) return null;
        CalzadoDTO dto = new CalzadoDTO();
        dto.setId(c.getId());
        dto.setStock(c.getStock());
        dto.setEstado(c.getEstado());
        if (c.getProducto() != null) dto.setIdProducto(c.getProducto().getId());
        if (c.getColor() != null) dto.setIdColor(c.getColor().getId());
        if (c.getTalla() != null) dto.setIdTalla(c.getTalla().getId());
        dto.setIsEnabled(c.isEnabled());
        return dto;
    }

    public Calzado toEntityFromCreateDto(com.cibertec.dto.request.CalzadoCreacionDTO dto) {
        Calzado c = new Calzado();
        c.setStock(dto.getStock());
        c.setEstado(dto.getEstado());
        c.setEnabled(true);
        c.setDelete(false);
        if (dto.getIdProducto() != null) {
            com.cibertec.entity.Producto prod = new com.cibertec.entity.Producto();
            prod.setId(dto.getIdProducto());
            c.setProducto(prod);
        }
        if (dto.getIdColor() != null) {
            com.cibertec.entity.Color col = new com.cibertec.entity.Color();
            col.setId(dto.getIdColor());
            c.setColor(col);
        }
        if (dto.getIdTalla() != null) {
            com.cibertec.entity.Talla t = new com.cibertec.entity.Talla();
            t.setId(dto.getIdTalla());
            c.setTalla(t);
        }
        return c;
    }
}