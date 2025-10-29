package com.cibertec.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cibertec.entity.*;


@Repository
public interface IMaterialRepository extends JpaRepository<Material, Integer> {
    List<Material> findByIsEnabledTrueAndIsDeleteFalse();
}