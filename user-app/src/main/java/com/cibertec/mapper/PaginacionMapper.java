package com.cibertec.mapper;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.cibertec.dto.response.PaginacionResponse;

@Service
public class PaginacionMapper {

    public <T> PaginacionResponse<T> toPaginacionResponse(Page<T> page) {
        return new PaginacionResponse<T>(
                page.toList(),
                page.getNumber(),
                page.getSize(),
                (int)page.getTotalElements(),
                page.getTotalPages(),
                page.isLast(),
                page.isFirst()
        );
    }
}
