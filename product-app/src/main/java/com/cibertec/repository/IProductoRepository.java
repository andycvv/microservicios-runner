package com.cibertec.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cibertec.entity.*;


@Repository
public interface IProductoRepository extends JpaRepository<Producto, Integer> {
    List<Producto> findByIsEnabledTrueAndIsDeleteFalse();
}