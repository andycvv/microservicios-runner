package com.cibertec.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cibertec.entity.Categoria;

public interface ICategoriaRepository extends JpaRepository<Categoria, Integer> {
    Page<Categoria> findByIsDeleteFalseAndIsEnabledTrue(Pageable pageable);
}
