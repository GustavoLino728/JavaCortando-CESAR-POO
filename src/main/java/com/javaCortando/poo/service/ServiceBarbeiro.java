package com.javaCortando.poo.service;

import com.javaCortando.poo.model.Barbeiro;
import com.javaCortando.poo.model.Cliente;
import com.javaCortando.poo.model.Corte;
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
        atribuirHorarioFuncionamento(barbeiroInicial);
        criarBarbeiro(barbeiroInicial);
        System.out.println("Barbeiro Salvo com sucesso!");
    }

    public List<String> definirHorarioFuncionamento(Barbeiro barbeiro) {
        List<String> horarios = new ArrayList<>();

        Float horarioAtual = barbeiro.getHorarioInicial();
        Float horarioFinal = barbeiro.getHorarioFinal();
        Float tempoPorCorte = barbeiro.getTempoPorCorte();

        while (horarioAtual < horarioFinal) {
            int horas = horarioAtual.intValue();
            int minutos = (int) ((horarioAtual - horas) * 60);
            String horarioFormatado = String.format("%02d:%02d", horas, minutos);
            horarios.add(horarioFormatado);

            horarioAtual += tempoPorCorte;
        }

        return horarios;
    }

    private void atribuirHorarioFuncionamento(Barbeiro barbeiro) {
        List<String> horarios = definirHorarioFuncionamento(barbeiro);
        barbeiro.setHorariosDeFuncionamento(horarios);
    }

    public Barbeiro buscarBarbeiroPorId(Long id) {
        return repositoryBarbeiro.findById(id)
                .orElseThrow(() -> new RuntimeException("Barbeiro não encontrado"));
    }

    private void criarBarbeiro(Barbeiro barbeiro){
        repositoryBarbeiro.save(barbeiro);
    }

    public void removerBarbeiro(Barbeiro barbeiro){
        repositoryBarbeiro.delete(barbeiro);
    }

//    public Corte cancelarCorte(){ serviceCorte.removerCorte;}
//    public List<Corte> buscarCortePorBarbeiro(Barbeiro barbeiro){}

}
