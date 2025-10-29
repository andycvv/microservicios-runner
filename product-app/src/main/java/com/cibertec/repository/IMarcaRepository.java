package com.cibertec.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cibertec.entity.Marca;

public interface IMarcaRepository extends JpaRepository<Marca, Integer> {
    Page<Marca> findByIsDeleteFalseAndIsEnabledTrue(Pageable pageable);
}