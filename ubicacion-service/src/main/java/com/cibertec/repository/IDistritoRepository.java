package com.cibertec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cibertec.entity.Distrito;

@Repository
public interface IDistritoRepository extends JpaRepository<Distrito, Integer> {
    List<Distrito> findByProvincia_Id(Integer provinciaId);
    List<Distrito> findByProvincia_IdAndIsEnabledTrueAndIsDeleteFalse(Integer provinciaId);

    List<Distrito> findByProvincia_IdAndProvincia_Departamento_Id(Integer provinciaId, Integer departamentoId);
    List<Distrito> findByProvincia_IdAndProvincia_Departamento_IdAndIsEnabledTrueAndIsDeleteFalse(Integer provinciaId, Integer departamentoId);

    List<Distrito> findByProvincia_IdAndProvincia_Departamento_IdAndProvincia_Departamento_Pais_Id(Integer provinciaId, Integer departamentoId, Integer paisId);
    List<Distrito> findByProvincia_IdAndProvincia_Departamento_IdAndProvincia_Departamento_Pais_IdAndIsEnabledTrueAndIsDeleteFalse(Integer provinciaId, Integer departamentoId, Integer paisId);
}