package com.cibertec.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cibertec.entity.Color;

public interface IColorRepository extends JpaRepository<Color, Integer> {
    Page<Color> findByIsDeleteFalseAndIsEnabledTrue(Pageable pageable);
}
