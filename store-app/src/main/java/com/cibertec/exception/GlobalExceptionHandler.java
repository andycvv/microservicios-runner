package com.cibertec.exception;

import java.time.LocalDateTime;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.cibertec.dto.response.ErrorResponse;

import jakarta.persistence.NoResultException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler({ NoResultException.class })
	public ResponseEntity<ErrorResponse> handleMissingData(Exception ex, WebRequest request) {
		
		ErrorResponse error = new ErrorResponse();
		error.setTimestamp(LocalDateTime.now());
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setError(HttpStatus.NOT_FOUND.getReasonPhrase());
		error.setMessage(ex.getMessage());
		error.setPath(request.getDescription(false).replace("uri=", ""));

		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ErrorResponse> handleDataIntegrityViolation(Exception ex, WebRequest request) {
		
		ErrorResponse error = new ErrorResponse();
		error.setTimestamp(LocalDateTime.now());
		error.setStatus(HttpStatus.CONFLICT.value());
		error.setError(HttpStatus.CONFLICT.getReasonPhrase());
		error.setMessage(ex.getMessage());
		error.setPath(request.getDescription(false).replace("uri=", ""));

		return new ResponseEntity<>(error, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGenericException(Exception ex, WebRequest request) {
		ErrorResponse error = new ErrorResponse();
		error.setTimestamp(LocalDateTime.now());
		error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		error.setMessage("Error interno");
		error.setPath(request.getDescription(false).replace("uri=", ""));

		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
