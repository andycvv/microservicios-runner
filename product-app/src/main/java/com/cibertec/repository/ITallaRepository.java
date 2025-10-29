package com.cibertec.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cibertec.entity.Talla;

public interface ITallaRepository extends JpaRepository<Talla, Integer> {
    Page<Talla> findByIsDeleteFalseAndIsEnabledTrue(Pageable pageable);
}
