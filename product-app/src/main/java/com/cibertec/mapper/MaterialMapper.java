package com.cibertec.mapper;

import org.springframework.stereotype.Service;

import com.cibertec.dto.response.MaterialDTO;
import com.cibertec.entity.Material;

@Service
public class MaterialMapper {
    public MaterialDTO toDto(Material m) {
        if (m == null) return null;
        MaterialDTO dto = new MaterialDTO();
        dto.setId(m.getId());
        dto.setNombre(m.getNombre());
        dto.setIsEnabled(m.isEnabled());
        return dto;
    }
}
