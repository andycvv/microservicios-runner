package com.cibertec.mapper;

import org.springframework.stereotype.Service;

import com.cibertec.dto.response.BranchDTO;
import com.cibertec.entity.Branch;

@Service
public class BranchMapper {
    public BranchDTO toDto(Branch b) {
        if (b == null) return null;
        BranchDTO dto = new BranchDTO();
        dto.setId(b.getId());
        dto.setNombre(b.getNombre());
        if (b.getSubcategoria() != null) dto.setIdSubcategoria(b.getSubcategoria().getId());
        dto.setIsEnabled(b.isEnabled());
        return dto;
    }

    public Branch toEntityFromCreateDto(com.cibertec.dto.request.BranchCreacionDTO dto) {
        Branch b = new Branch();
        b.setNombre(dto.getNombre());
        b.setEnabled(true);
        b.setDelete(false);
        if (dto.getIdSubcategoria() != null) {
            com.cibertec.entity.Subcategoria s = new com.cibertec.entity.Subcategoria();
            s.setId(dto.getIdSubcategoria());
            b.setSubcategoria(s);
        }
        return b;
    }
}
