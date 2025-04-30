package com.javaCortando.poo.repository;

import com.javaCortando.poo.model.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
public class RepositoryClienteTest {

    @Autowired
    private RepositoryCliente repositoryCliente;

    @Autowired
    private EntityManager entityManager;

    @BeforeEach
    void limpar() {
        repositoryCliente.deleteAll();
    }

//    @Test
//    void deveRetornarTrueSeUsernameExistir() {
//        Cliente cliente = new Cliente();
//        cliente.setEmail("user@email.com");
//        cliente.setUsername("cliente");
//        entityManager.persist(cliente);
//
//        entityManager.flush();
//
//        boolean existe = repositoryCliente.existsByUsername("cliente");
//        assertTrue(existe);
//    }

    @Test
    void deveRetornarFalseSeUsernameNaoExistir() {
        boolean existe = repositoryCliente.existsByUsername("naoExiste");
        assertFalse(existe);
    }
}
