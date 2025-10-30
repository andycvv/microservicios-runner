package com.cibertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cibertec.entity.Boleta;
import java.util.List;


public interface IBoletaRepository extends JpaRepository<Boleta, Integer> {
	List<Boleta> findByIdTrabajador(Integer idTrabajador);
	List<Boleta> findByIdUsuario(Integer idUsuario);
}
