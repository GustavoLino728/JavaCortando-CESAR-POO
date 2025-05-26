package com.javaCortando.poo.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.javaCortando.poo.component.RemoverCortesAntigos;
import com.javaCortando.poo.model.Barbeiro;
import com.javaCortando.poo.model.Cliente;
import com.javaCortando.poo.repository.RepositoryBarbeiro;
import com.javaCortando.poo.repository.RepositoryCliente;
import com.javaCortando.poo.service.ServiceBarbeiro;
import com.javaCortando.poo.service.ServiceCliente;

@SpringBootApplication
public class StartApp implements CommandLineRunner {

    @Autowired
    private ServiceBarbeiro serviceBarbeiro;

    @Autowired
    private ServiceCliente serviceCliente;

    @Autowired
    private RepositoryBarbeiro repositoryBarbeiro;

    @Autowired
    private RepositoryCliente repositoryCliente;

    @Autowired
    private RemoverCortesAntigos removerCortesAntigos;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        removerCortesAntigos.limparCortesAntigos();

        Barbeiro barbeiroDefault = repositoryBarbeiro.findByUsername("barbeiro");
        if (barbeiroDefault == null) {
            Barbeiro barbeiroInicial = new Barbeiro();
            barbeiroInicial.setUsername("barbeiro");
            barbeiroInicial.setTelefone("81940028922");
            barbeiroInicial.setEmail("joao@barbearia.com");
            barbeiroInicial.setPassword("123");
            barbeiroInicial.setHorarioInicial(8.0f);
            barbeiroInicial.setHorarioFinal(18.0f);
            barbeiroInicial.setTempoPorCorte(0.5f);
            barbeiroInicial.getRoles().add("MANAGERS");
            serviceBarbeiro.atribuirHorarioFuncionamento(barbeiroInicial);
            repositoryBarbeiro.save(barbeiroInicial);
            System.out.println("Barbeiro Salvo com sucesso!");
        }

        Cliente clienteDefault = repositoryCliente.findByUsername("cliente");
        if (clienteDefault == null) {
            clienteDefault = new Cliente();
            clienteDefault.setUsername("cliente");
            clienteDefault.setEmail("user@email.com");
            clienteDefault.setPassword("user123");
            clienteDefault.setTelefone("00000000000");
            clienteDefault.getRoles().add("USERS");
            serviceCliente.criarCliente(clienteDefault);
            System.out.println("Cliente Salvo com sucesso!");
        }
    }
}