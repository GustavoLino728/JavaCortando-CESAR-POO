package com.javaCortando.poo.repository;

import com.javaCortando.poo.model.Cliente;
import com.javaCortando.poo.model.Corte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface RepositoryCortes extends JpaRepository<Corte, Long> {
    @Query("SELECT c FROM Corte c WHERE c.data = :data AND c.horario = :horario")
    Corte findByDataAndHorario(
            @Param("data") LocalDate data,
            @Param("horario") Float horario
    );

    @Query("SELECT c FROM Corte c WHERE c.data < :data OR c.data IS NULL")
    List<Corte> findByDataBeforeOrDataIsNull(@Param("data") LocalDate data);

}
