package com.cibertec.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cibertec.user.model.Distrito;

@Repository
public interface IDistritoRepository extends JpaRepository<Distrito, Integer> {

	boolean existsByNombre(String nombre);
}
