package com.cibertec.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaginacionResponse<T> {
    private List<T> content;
    private int pageNumber;
    private int pageSize;
    private int totalElements;
    private int totalPages;
    private boolean last;
    private boolean first;
}
