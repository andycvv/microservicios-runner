package com.cibertec.user.service;

import com.cibertec.user.dto.request.LoginDTO;
import com.cibertec.user.dto.request.RegisterUserDTO;
import com.cibertec.user.dto.request.UpdatePasswordDTO;
import com.cibertec.user.dto.request.UpdateUserDTO;
import com.cibertec.user.dto.response.UserResponse;
import com.cibertec.user.model.Usuario;

public interface AccountService {
	public Usuario findByEmailInternal(String email);
	
	public String signin(LoginDTO request);
	
	public void registerUser(RegisterUserDTO request);
	
	public void updateUser(UpdateUserDTO request);
	
	public void updatePassword(UpdatePasswordDTO request);
	
	public UserResponse getUsuarioLogueado(); 

}