package com.cibertec.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cibertec.entity.Subcategoria;

public interface ISubcategoriaRepository extends JpaRepository<Subcategoria, Integer> {
    Page<Subcategoria> findByIsDeleteFalseAndIsEnabledTrue(Pageable pageable);
}
