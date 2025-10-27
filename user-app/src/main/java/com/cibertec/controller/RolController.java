package com.cibertec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.dto.response.RolDTO;
import com.cibertec.service.RolService;
@RestController
@RequestMapping("/api/roles")
public class RolController {
	@Autowired
	private RolService rolService;
	
	@GetMapping
	public List<RolDTO> listarRoles() {
		return rolService.listarRoles();
	}
	
	@GetMapping("/activos")
	public List<RolDTO> listarRolesActivos() {
		return rolService.listarRolesActivos();
	}
}
