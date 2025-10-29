package com.cibertec.mapper;

import org.springframework.stereotype.Service;

import com.cibertec.dto.response.CategoriaDTO;
import com.cibertec.entity.Categoria;

@Service
public class CategoriaMapper {
    public CategoriaDTO toDto(Categoria c) {
        if (c == null) return null;
        CategoriaDTO dto = new CategoriaDTO();
        dto.setId(c.getId());
        dto.setNombre(c.getNombre());
        dto.setIsEnabled(c.isEnabled());
        return dto;
    }
}
