package com.cibertec.service;

import java.util.List;

import com.cibertec.dto.response.RolDTO;
import com.cibertec.entity.Rol;

public interface RolService {
	List<RolDTO> listarRoles();
	List<RolDTO> listarRolesActivos();
}
