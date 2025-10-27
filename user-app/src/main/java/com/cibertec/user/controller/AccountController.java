package com.cibertec.user.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.user.dto.request.UpdatePasswordDTO;
import com.cibertec.user.dto.request.UpdateUserDTO;
import com.cibertec.user.dto.request.UsuarioCreacionDTO;
import com.cibertec.user.dto.response.SuccessResponse;
import com.cibertec.user.dto.response.UserResponse;
import com.cibertec.user.service.implement.AccountServiceImp;

@RestController
@RequestMapping("/api/account")
public class AccountController {

	@Autowired
	private AccountServiceImp accountService;
	
	@PostMapping("/register")
	public ResponseEntity<SuccessResponse<String>> registerUser(@RequestBody UsuarioCreacionDTO request) {
		
		accountService.registerUser(request);
		
		SuccessResponse<String> success = SuccessResponse.<String>builder()
			        .timestamp(LocalDateTime.now())
			        .status(HttpStatus.CREATED.value())
			        .success(HttpStatus.CREATED.getReasonPhrase())
			        .response("El usuario se creo correctamente")
			        .build();
		 
		return ResponseEntity.status(HttpStatus.CREATED).body(success);
	}

	@PutMapping("/update-user")
	public ResponseEntity<SuccessResponse<String>> updateUser(@RequestBody UpdateUserDTO request) {
		accountService.updateUser(request);
		
		 SuccessResponse<String> success = SuccessResponse.<String>builder()
			        .timestamp(LocalDateTime.now())
			        .status(HttpStatus.OK.value())
			        .success(HttpStatus.OK.getReasonPhrase())
			        .response("El usuario fue actualizado")
			        .build();
		
		return ResponseEntity.ok(success);
	}

	@PutMapping("/actualizar-clave")
	public ResponseEntity<SuccessResponse<String>> updatePassword(@RequestBody UpdatePasswordDTO request) {
		accountService.updatePassword(request);
		
		SuccessResponse<String> success = SuccessResponse.<String>builder()
			        .timestamp(LocalDateTime.now())
			        .status(HttpStatus.OK.value())
			        .success(HttpStatus.OK.getReasonPhrase())
			        .response("La contraseña se cambio correctamente")
			        .build();
		
		return ResponseEntity.ok(success);
	}

	@GetMapping("/user-data")
	public ResponseEntity<SuccessResponse<UserResponse>> getUsuarioLogueado() {
		UserResponse response = accountService.getUsuarioLogueado();
		
		SuccessResponse<UserResponse> success = SuccessResponse.<UserResponse>builder()
			        .timestamp(LocalDateTime.now())
			        .status(HttpStatus.OK.value())
			        .success(HttpStatus.OK.getReasonPhrase())
			        .response(response)
			        .build();
		
		return ResponseEntity.ok(success);
	}
}