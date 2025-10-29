package com.cibertec.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cibertec.entity.Trabajador;

@Repository
public interface ITrabajadorRepository extends JpaRepository<Trabajador, Integer>{
    // isDelete = false AND isEnabled = true
    Page<Trabajador> findByIsDeleteFalseAndIsEnabledTrue(Pageable pageable);

    // inactive: deleted OR disabled
    Page<Trabajador> findByIsDeleteTrueOrIsEnabledFalse(Pageable pageable);
}