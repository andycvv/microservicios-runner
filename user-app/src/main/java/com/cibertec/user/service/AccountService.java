package com.cibertec.user.service;

import com.cibertec.entity.Usuario;
import com.cibertec.user.dto.request.UpdatePasswordDTO;
import com.cibertec.user.dto.request.UpdateUserDTO;
import com.cibertec.user.dto.request.UsuarioCreacionDTO;
import com.cibertec.user.dto.response.UserResponse;

public interface AccountService {
	public Usuario findByEmailInternal(String email);
	
	public void registerUser(UsuarioCreacionDTO request);
	
	public void updateUser(UpdateUserDTO request);
	
	public void updatePassword(UpdatePasswordDTO request);
	
	public UserResponse getUsuarioLogueado(); 

}