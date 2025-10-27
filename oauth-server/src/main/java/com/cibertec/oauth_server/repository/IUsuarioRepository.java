package com.cibertec.oauth_server.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cibertec.entity.Usuario;

public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByEmail(String correo);
}
