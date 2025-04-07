package com.javaCortando.poo.service;

import com.javaCortando.poo.model.Barbeiro;
import com.javaCortando.poo.repository.RepositoryBarbeiro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceBarbeiro {
    @Autowired
    private RepositoryBarbeiro repositoryBarbeiro;

    public void salvarBarbeiroInicial(){
        Barbeiro barbeiroInicial = new Barbeiro();
        barbeiroInicial.setNome("João");
        barbeiroInicial.setTelefone("81940028922");
        barbeiroInicial.setEmail("joao@barbearia.com");
        barbeiroInicial.setSenha("senha123");
        barbeiroInicial.setHorarioInicial(8.0f);
        barbeiroInicial.setHorarioFinal(18.0f);
        barbeiroInicial.setTempoPorCorte(0.5f);
        repositoryBarbeiro.save(barbeiroInicial);
        System.out.println("Barbeiro Salvo com sucesso!");
    }

    public void definirHorarioFuncionamento(Barbeiro barbeiro){

    }

}
