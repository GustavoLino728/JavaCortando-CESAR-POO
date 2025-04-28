package com.javaCortando.poo.repository;

import com.javaCortando.poo.model.Barbeiro;
import com.javaCortando.poo.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RepositoryCliente extends JpaRepository<Cliente, Long> {
    @Query("SELECT e FROM Cliente e JOIN FETCH e.roles WHERE e.username= (:username)")
    public Cliente findByUsername(@Param("username") String username);

    boolean existsByUsername(String username);
}
