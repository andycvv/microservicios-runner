package com.cibertec.mapper;

import org.springframework.stereotype.Service;

import com.cibertec.dto.response.MarcaDTO;
import com.cibertec.entity.Marca;

@Service
public class MarcaMapper {
    public MarcaDTO toDto(Marca m) {
        if (m == null) return null;
        MarcaDTO dto = new MarcaDTO();
        dto.setId(m.getId());
        dto.setNombre(m.getNombre());
        dto.setIsEnabled(m.isEnabled());
        return dto;
    }
}
