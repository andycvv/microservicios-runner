package com.cibertec.mapper;

import org.springframework.stereotype.Service;

import com.cibertec.dto.response.SubcategoriaDTO;
import com.cibertec.entity.Categoria;
import com.cibertec.entity.Subcategoria;

@Service
public class SubcategoriaMapper {
    public SubcategoriaDTO toDto(Subcategoria s) {
        if (s == null) return null;
        SubcategoriaDTO dto = new SubcategoriaDTO();
        dto.setId(s.getId());
        dto.setNombre(s.getNombre());
        if (s.getCategoria() != null) dto.setIdCategoria(s.getCategoria().getId());
        dto.setIsEnabled(s.isEnabled());
        return dto;
    }

    public Subcategoria toEntityFromCreateDto(com.cibertec.dto.request.SubcategoriaCreacionDTO dto) {
        Subcategoria s = new Subcategoria();
        s.setNombre(dto.getNombre());
        s.setEnabled(true);
        s.setDelete(false);
        if (dto.getIdCategoria() != null) {
            Categoria c = new Categoria(); c.setId(dto.getIdCategoria()); s.setCategoria(c);
        }
        return s;
    }
}
