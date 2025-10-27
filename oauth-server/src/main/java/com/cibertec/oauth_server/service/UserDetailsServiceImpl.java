package com.cibertec.oauth_server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cibertec.entity.Usuario;
import com.cibertec.oauth_server.repository.IUsuarioRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private IUsuarioRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        logger.info("Looking up user by correo={}", mail);
        Usuario usuario = userRepository.findByEmail(mail)
            .orElseThrow(() -> {
                logger.warn("User not found for correo={}", mail);
                return new UsernameNotFoundException("Usuario no encontrado");
            });

        String pw = usuario.getClave();
        boolean looksLikeBcrypt = pw != null && (pw.startsWith("$2a$") || pw.startsWith("$2b$") || pw.startsWith("$2y$"));
        logger.debug("Found user correo={}, role={}, passwordLooksLikeBCrypt={}", usuario.getEmail(), usuario.getRol().getNombre(), looksLikeBcrypt);

        return User.builder()
            .username(usuario.getEmail())
            .password(usuario.getClave())
            .roles(usuario.getRol().getNombre())
            .disabled(usuario.isDelete() || !usuario.isEnabled())
            .build();
    }
}