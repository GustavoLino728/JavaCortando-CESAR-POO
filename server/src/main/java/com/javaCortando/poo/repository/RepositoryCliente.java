package com.javaCortando.poo.repository;

import com.javaCortando.poo.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryCliente extends JpaRepository<Cliente, Long> {
}
