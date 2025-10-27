package com.cibertec.service;

import com.cibertec.dto.request.UpdatePasswordDTO;
import com.cibertec.dto.request.UpdateUserDTO;
import com.cibertec.dto.request.UsuarioCreacionDTO;
import com.cibertec.dto.response.UserResponse;
import com.cibertec.entity.Usuario;

public interface AccountService {
	public Usuario findByEmailInternal(String email);
	
	public void registerUser(UsuarioCreacionDTO request);
	
	public void updateUser(UpdateUserDTO request);
	
	public void updatePassword(UpdatePasswordDTO request);
	
	public UserResponse getUsuarioLogueado(); 

}