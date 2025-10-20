package com.cibertec.user.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SuccessResponse<T> {
    private LocalDateTime timestamp;
    private int status;
    private String success;
    private T response;
}
