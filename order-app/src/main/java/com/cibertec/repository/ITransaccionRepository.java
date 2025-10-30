package com.cibertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cibertec.entity.Transaccion;

public interface ITransaccionRepository extends JpaRepository<Transaccion, Integer> {

}
