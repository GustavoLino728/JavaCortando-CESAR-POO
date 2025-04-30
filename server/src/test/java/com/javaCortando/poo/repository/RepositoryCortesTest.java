package com.javaCortando.poo.repository;

import com.javaCortando.poo.model.Corte;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
public class RepositoryCortesTest {

    @Autowired
    private RepositoryCortes repositoryCortes;

    @Autowired
    private EntityManager entityManager;

    @BeforeEach
    void setUp() {
        repositoryCortes.deleteAll();
    }

    @Test
    void deveEncontrarCortesComDataAntesOuNula() {
        LocalDate hoje = LocalDate.now();

        Corte passado = new Corte();
        passado.setData(hoje.minusDays(2));
        entityManager.persist(passado);

        Corte nulo = new Corte();
        nulo.setData(null);
        entityManager.persist(nulo);

        Corte futuro = new Corte();
        futuro.setData(hoje.plusDays(1));
        entityManager.persist(futuro);

        entityManager.flush();

        List<Corte> encontrados = repositoryCortes.findByDataBeforeOrDataIsNull(hoje);
        assertEquals(2, encontrados.size());
        assertTrue(encontrados.contains(passado));
        assertTrue(encontrados.contains(nulo));
    }

    @Test
    void deveEncontrarCortePorDataEHorario() {
        LocalDate hoje = LocalDate.now();

        Corte corte = new Corte();
        corte.setData(hoje);
        corte.setHorario(14.5f);
        entityManager.persist(corte);

        entityManager.flush();

        Corte resultado = repositoryCortes.findByDataAndHorario(hoje, 14.5f);
        assertNotNull(resultado);
        assertEquals(14.5f, resultado.getHorario());
        assertEquals(hoje, resultado.getData());
    }
}
