package com.javaCortando.poo.security;

import com.javaCortando.poo.model.Barbeiro;
import com.javaCortando.poo.model.Cliente;
import com.javaCortando.poo.repository.RepositoryBarbeiro;
import com.javaCortando.poo.repository.RepositoryCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class SecurityDatabaseService implements UserDetailsService {

    @Autowired
    private RepositoryCliente repositoryCliente;

    @Autowired
    private RepositoryBarbeiro repositoryBarbeiro;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Cliente cliente = repositoryCliente.findByUsername(username);
        if (cliente != null) {
            return buildUserDetails(cliente.getUsername(), cliente.getPassword(), cliente.getRoles());
        }

        Barbeiro barbeiro = repositoryBarbeiro.findByUsername(username);
        if (barbeiro != null) {
            return buildUserDetails(barbeiro.getUsername(), barbeiro.getPassword(), barbeiro.getRoles());
        }

        throw new UsernameNotFoundException("Usuário não encontrado: " + username);
    }

    private UserDetails buildUserDetails(String username, String password, java.util.List<String> roles) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role)));
        return new org.springframework.security.core.userdetails.User(username, password, authorities);
    }
}
