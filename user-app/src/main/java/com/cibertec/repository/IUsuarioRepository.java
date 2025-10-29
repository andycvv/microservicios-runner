package com.cibertec.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cibertec.entity.Usuario;

public interface IUsuarioRepository extends JpaRepository<Usuario, Integer>{
    Optional<Usuario> findByEmail(String mail);
    Page<Usuario> findByIsDeleteFalseAndIsEnabledTrue(Pageable pageable);
}
