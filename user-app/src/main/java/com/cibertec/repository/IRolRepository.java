package com.cibertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cibertec.entity.Rol;

import java.util.Optional;


public interface IRolRepository extends JpaRepository<Rol, Integer> {
	Optional<Rol> findByNombre(String nombre);
}
