package com.cibertec.mapper;

import org.springframework.stereotype.Service;

import com.cibertec.dto.response.ColorDTO;
import com.cibertec.entity.Color;

@Service
public class ColorMapper {
    public ColorDTO toDto(Color c) {
        if (c == null) return null;
        ColorDTO dto = new ColorDTO();
        dto.setId(c.getId());
        dto.setNombre(c.getNombre());
        dto.setIsEnabled(c.isEnabled());
        return dto;
    }

    public Color toEntityFromCreateDto(com.cibertec.dto.request.ColorCreacionDTO dto) {
        Color c = new Color();
        c.setNombre(dto.getNombre());
        c.setEnabled(true);
        c.setDelete(false);
        return c;
    }
}
