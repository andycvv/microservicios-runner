package com.cibertec.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cibertec.entity.Producto;

public interface IProductoRepository extends JpaRepository<Producto, Integer> {
    Page<Producto> findByIsDeleteFalseAndIsEnabledTrue(Pageable pageable);
}
