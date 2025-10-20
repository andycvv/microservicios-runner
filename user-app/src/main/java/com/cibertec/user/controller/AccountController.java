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

import com.cibertec.user.dto.request.RegisterUserDTO;
import com.cibertec.user.dto.request.UpdatePasswordDTO;
import com.cibertec.user.dto.request.UpdateUserDTO;
import com.cibertec.user.dto.response.SuccessResponse;
import com.cibertec.user.dto.response.UserResponse;
import com.cibertec.user.service.implement.AccountServiceImp;

@RestController
@RequestMapping("/api/account")
public class AccountController {

	@Autowired
	private AccountServiceImp userService;
	
	@PostMapping("/register")
	public ResponseEntity<SuccessResponse<String>> registerUser(@RequestBody RegisterUserDTO request) {
		
		userService.registerUser(request);
		
		SuccessResponse<String> success = SuccessResponse.<String>builder()
			        .timestamp(LocalDateTime.now())
			        .status(HttpStatus.CREATED.value())
			        .success(HttpStatus.CREATED.getReasonPhrase())
			        .response("El usuario se creo correctamente")
			        .build();
		 
		return ResponseEntity.status(HttpStatus.CREATED).body(success);
	}

	@PutMapping("/update-user") // actualizar datos de usuario 
	public ResponseEntity<SuccessResponse<String>> updateUser(@RequestBody UpdateUserDTO request) {
		userService.updateUser(request);
		
		 SuccessResponse<String> success = SuccessResponse.<String>builder()
			        .timestamp(LocalDateTime.now())
			        .status(HttpStatus.OK.value())
			        .success(HttpStatus.OK.getReasonPhrase())
			        .response("El usuario fue actualizado")
			        .build();
		
		return ResponseEntity.ok(success);
	}

	@PutMapping("/update-password") // actualizar contraseña
	public ResponseEntity<SuccessResponse<String>> updatePassword(@RequestBody UpdatePasswordDTO request) {
		userService.updatePassword(request);
		
		SuccessResponse<String> success = SuccessResponse.<String>builder()
			        .timestamp(LocalDateTime.now())
			        .status(HttpStatus.OK.value())
			        .success(HttpStatus.OK.getReasonPhrase())
			        .response("La contraseña se cambio correctamente")
			        .build();
		
		return ResponseEntity.ok(success);
	}

	@GetMapping("/user-data") // datos del usuario
	public ResponseEntity<SuccessResponse<UserResponse>> getUsuarioLogueado() {
		UserResponse response = userService.getUsuarioLogueado();
		
		SuccessResponse<UserResponse> success = SuccessResponse.<UserResponse>builder()
			        .timestamp(LocalDateTime.now())
			        .status(HttpStatus.OK.value())
			        .success(HttpStatus.OK.getReasonPhrase())
			        .response(response)
			        .build();
		
		return ResponseEntity.ok(success);
	}

//	@PostMapping("/sign-in")
//	public ResponseEntity<SuccessResponse<String>> signin(@RequestBody LoginDTO request) {
//		String mensaje = userService.signin(request);
//		
//		SuccessResponse<String> success = SuccessResponse.<String>builder()
//			        .timestamp(LocalDateTime.now())
//			        .status(HttpStatus.OK.value())
//			        .success(HttpStatus.OK.getReasonPhrase())
//			        .response(mensaje)
//			        .build();
//		 
//		return ResponseEntity.ok(success);
//	}
}