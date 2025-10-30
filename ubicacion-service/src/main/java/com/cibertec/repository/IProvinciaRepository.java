package com.cibertec.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cibertec.entity.Provincia;

@Repository
public interface IProvinciaRepository extends JpaRepository<Provincia, Integer> {
    List<Provincia> findByDepartamento_Id(Integer departamentoId);
    List<Provincia> findByDepartamento_IdAndIsEnabledTrueAndIsDeleteFalse(Integer departamentoId);

    // provincias that belong to a given departamento and that departamento belongs to a given pais
    List<Provincia> findByDepartamento_IdAndDepartamento_Pais_Id(Integer departamentoId, Integer paisId);
    List<Provincia> findByDepartamento_IdAndDepartamento_Pais_IdAndIsEnabledTrueAndIsDeleteFalse(Integer departamentoId, Integer paisId);

    Page<Provincia> findByIsEnabledTrueAndIsDeleteFalse(Pageable pageable);
}