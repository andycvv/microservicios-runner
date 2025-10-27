package com.cibertec.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cibertec.entity.Usuario;
import com.cibertec.repository.IUsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
    private IUsuarioRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
    	
        Usuario usuario = userRepository.findByEmail(mail)
            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        return User.builder()
            .username(usuario.getEmail())
            .password(usuario.getClave())
            .roles(usuario.getRol().getNombre()) 
            .build();
    }
}