package com.javaCortando.poo.service;

import com.javaCortando.poo.model.Barbeiro;
import com.javaCortando.poo.repository.RepositoryBarbeiro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        definirHorarioFuncionamento(barbeiroInicial);
        repositoryBarbeiro.save(barbeiroInicial);
        System.out.println("Barbeiro Salvo com sucesso!");
    }

    public List<Float> definirHorarioFuncionamento(Barbeiro barbeiro) {
        List<Float> horarios = new ArrayList<>();

        Float horarioAtual = barbeiro.getHorarioInicial();
        Float horarioFinal = barbeiro.getHorarioFinal();
        Float tempoPorCorte = barbeiro.getTempoPorCorte();

        while (horarioAtual < horarioFinal) {
            horarios.add(horarioAtual);
            horarioAtual += tempoPorCorte;
        }

        for (Float horario : horarios) {
            System.out.printf("%.2f \uD83D\uDD52\n", horario);
        }

        return horarios;
    }

}
