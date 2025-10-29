package com.cibertec.mapper;

import org.springframework.stereotype.Service;

import com.cibertec.dto.response.TallaDTO;
import com.cibertec.entity.Talla;

@Service
public class TallaMapper {
    public TallaDTO toDto(Talla t) {
        if (t == null) return null;
        TallaDTO dto = new TallaDTO();
        dto.setId(t.getId());
        dto.setNumero(t.getNumero());
        dto.setIsEnabled(t.isEnabled());
        return dto;
    }

    public Talla toEntityFromCreateDto(com.cibertec.dto.request.TallaCreacionDTO dto) {
        Talla t = new Talla();
        t.setNumero(dto.getNumero());
        t.setEnabled(true);
        t.setDelete(false);
        return t;
    }
}
