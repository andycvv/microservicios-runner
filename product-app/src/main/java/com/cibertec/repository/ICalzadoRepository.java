package com.cibertec.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cibertec.entity.Calzado;

public interface ICalzadoRepository extends JpaRepository<Calzado, Integer> {
    Page<Calzado> findByIsDeleteFalseAndIsEnabledTrue(Pageable pageable);
}
