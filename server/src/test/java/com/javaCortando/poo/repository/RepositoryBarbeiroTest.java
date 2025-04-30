package com.javaCortando.poo.repository;

import com.javaCortando.poo.model.Barbeiro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test")
public class RepositoryBarbeiroTest {

    @Autowired
    private RepositoryBarbeiro repositoryBarbeiro;

    @Test
    void deveVerificarSeUsernameExiste() {
        Barbeiro barbeiro = new Barbeiro();
        barbeiro.setUsername("barbeiro");
        repositoryBarbeiro.save(barbeiro);

        boolean existe = repositoryBarbeiro.existsByUsername("barbeiro");
        assertThat(existe).isTrue();
    }

    @Test
    void deveVerificarSeUsernameNaoExiste() {
        boolean existe = repositoryBarbeiro.existsByUsername("nao.existe");
        assertThat(existe).isFalse();
    }
}