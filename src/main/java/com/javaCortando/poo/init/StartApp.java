package com.javaCortando.poo.init;

import com.javaCortando.poo.model.Barbeiro;
import com.javaCortando.poo.repository.RepositoryBarbeiro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StartApp implements CommandLineRunner {

    @Autowired
    private RepositoryBarbeiro barbeiroRepository;

    public static void main(String[] args) {
        SpringApplication.run(StartApp.class, args);
    }

    @Override
    public void run(String... args) {
        Barbeiro barbeiroInicial = new Barbeiro();
        barbeiroInicial.setNome("João");
        barbeiroInicial.setTelefone("11999999999");
        barbeiroInicial.setEmail("joao@barbearia.com");
        barbeiroInicial.setSenha("senha123");
        barbeiroInicial.setHorarioInicial(8.0f);
        barbeiroInicial.setHorarioFinal(18.0f);
        barbeiroInicial.setTempoPorCorte(0.5f);
        barbeiroRepository.save(barbeiroInicial);
        System.out.println("Barbeiro Salvo com sucesso!");
    }
}