package com.cibertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cibertec.entity.Transaccion;
import com.cibertec.entity.TransaccionId;

public interface ITransaccionRepository extends JpaRepository<Transaccion, TransaccionId> {

}
