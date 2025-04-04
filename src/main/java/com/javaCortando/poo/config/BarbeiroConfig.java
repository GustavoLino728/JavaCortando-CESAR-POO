package com.javaCortando.poo.config;

import com.javaCortando.poo.model.Barbeiro;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BarbeiroConfig {

    @Bean
    public Barbeiro defaultBarbeiro() {
        Barbeiro barbeiro = new Barbeiro();
        barbeiro.setNome("Barbeiro");
        barbeiro.setEmail("barbeiro@gmail.com");
        barbeiro.setTelefone("81995343");
        barbeiro.setSenha("barbeiro");
        barbeiro.setHorarioFinal(20f);
        barbeiro.setHorarioInicial(3f);
        barbeiro.setTempoPorCorte(30f);
        barbeiro.setId(1L);

        barbeiro.setHorariosDeFuncionamento(
                java.util.Arrays.asList(9f, 10f, 11f, 12f)
        );

        return barbeiro;
    }
}
