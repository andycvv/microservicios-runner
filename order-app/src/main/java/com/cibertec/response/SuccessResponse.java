package com.cibertec.response;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

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

    // Convenience factory that fills timestamp, status and success reason phrase
    public static <T> SuccessResponse<T> of(HttpStatus status, T response) {
        return SuccessResponse.<T>builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .success(status.getReasonPhrase())
                .response(response)
                .build();
    }

    public static <T> SuccessResponse<T> ok(T response) {
        return of(HttpStatus.OK, response);
    }

    public static <T> SuccessResponse<T> created(T response) {
        return of(HttpStatus.CREATED, response);
    }
}