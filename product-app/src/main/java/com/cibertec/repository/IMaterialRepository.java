package com.cibertec.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cibertec.entity.Material;

public interface IMaterialRepository extends JpaRepository<Material, Integer> {
    Page<Material> findByIsDeleteFalseAndIsEnabledTrue(Pageable pageable);
}
