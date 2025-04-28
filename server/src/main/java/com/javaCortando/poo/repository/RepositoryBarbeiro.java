package com.javaCortando.poo.repository;

import com.javaCortando.poo.model.Barbeiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RepositoryBarbeiro extends JpaRepository<Barbeiro, Long> {
    @Query("SELECT e FROM Barbeiro e JOIN FETCH e.roles WHERE e.username= (:username)")
    public Barbeiro findByUsername(@Param("username") String username);

    boolean existsByUsername(String username);
}
