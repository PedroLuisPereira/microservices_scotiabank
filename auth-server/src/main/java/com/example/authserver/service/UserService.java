package com.example.authserver.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.authserver.dto.Usuario;
import com.example.authserver.feign.UserFeignClient;

import feign.FeignException;

@Service
public class UserService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserFeignClient userFeignClient;

    /**
     * Se encarga de autenticar al usuario
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        try {

            Usuario usuario = userFeignClient.getUserByUsername(username);

            logger.info("Se consulta al usuario: " + usuario);

            List<GrantedAuthority> grantedAuthorities = usuario.getRoles()
                    .stream()
                    .map(role -> new SimpleGrantedAuthority(role.getNombre()))
                    .peek(authority -> logger.info("Rol: " + authority.getAuthority()))
                    .collect(Collectors.toList());

            return new User(usuario.getUsername(),
                    usuario.getPassword(),
                    usuario.getEnabled(),
                    true,
                    true,
                    true,
                    grantedAuthorities);

        } catch (FeignException e) {
            throw new UsernameNotFoundException("Usuario no existe");
        }
    }

}
