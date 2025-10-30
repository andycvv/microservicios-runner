package com.cibertec.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.dto.response.RolDTO;
import com.cibertec.repository.IRolRepository;
import com.cibertec.service.RolService;

@Service
public class RolServiceImpl implements RolService {

	@Autowired
	private IRolRepository rolRepository;
	
	@Override
	public List<RolDTO> listarRoles() {
		return rolRepository.findAll().stream()
				.map(rol -> {
					RolDTO r = new RolDTO();
					r.setId(rol.getId());
					r.setNombre(rol.getNombre());
					return r;
				})
				.toList();
	}

	@Override
	public List<RolDTO> listarRolesActivos() {
		return rolRepository.findAll().stream()
				.filter(rol -> !rol.isDelete() && rol.isEnabled())
				.map(rol -> {
					RolDTO r = new RolDTO();
					r.setId(rol.getId());
					r.setNombre(rol.getNombre());
					return r;
				})
				.toList();
	}

}
