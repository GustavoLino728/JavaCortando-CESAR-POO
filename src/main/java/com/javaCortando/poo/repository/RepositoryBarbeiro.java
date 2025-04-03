package com.javaCortando.poo.repository;

import com.javaCortando.poo.model.Barbeiro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryBarbeiro extends JpaRepository<Barbeiro, Long> {
}
