package com.cibertec.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.cibertec.entity.Trabajador;
import com.cibertec.response.TrabajadorDTO;

@Component
public class TrabajadorMapper {

    public TrabajadorDTO toDto(Trabajador t) {
        if (t == null) return null;
        TrabajadorDTO dto = new TrabajadorDTO();
        dto.setId(t.getId());
        dto.setSalario(t.getSalario());
        dto.setHorasLaborales(t.getHorasLaborales());
        if (t.getUsuario() != null) {
            dto.setNombre(t.getUsuario().getNombre());
            dto.setApellido(t.getUsuario().getApellido());
            dto.setEmail(t.getUsuario().getEmail());
        }
        dto.setEnabled(t.isEnabled());
        return dto;
    }

    public List<TrabajadorDTO> toDtos(List<Trabajador> list) {
        if (list == null) return null;
        return list.stream().map(this::toDto).collect(Collectors.toList());
    }
}