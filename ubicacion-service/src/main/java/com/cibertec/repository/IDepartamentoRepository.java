package com.cibertec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cibertec.entity.Departamento;

@Repository
public interface IDepartamentoRepository extends JpaRepository<Departamento, Integer> {
    List<Departamento> findByPais_Id(Integer paisId);
    List<Departamento> findByPais_IdAndIsEnabledTrueAndIsDeleteFalse(Integer paisId);
}