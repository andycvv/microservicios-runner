package com.cibertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cibertec.entity.Tienda;

public interface ITiendaRepository extends JpaRepository<Tienda, Integer> {

}
