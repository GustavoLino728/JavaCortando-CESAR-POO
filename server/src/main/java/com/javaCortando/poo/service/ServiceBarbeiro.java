package com.javaCortando.poo.service;

import com.javaCortando.poo.dto.Login;
import com.javaCortando.poo.model.Barbeiro;
import com.javaCortando.poo.model.Cliente;
import com.javaCortando.poo.model.Corte;
import com.javaCortando.poo.repository.RepositoryBarbeiro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceBarbeiro {
    @Autowired
    private RepositoryBarbeiro repositoryBarbeiro;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Barbeiro findByUsername(Login login) {
        if (login == null || login.getUsername() == null) {
            return null;
        }

        // Adicione logs para depuração
        System.out.println("Buscando barbeiro com username: " + login.getUsername());

        Barbeiro barbeiro = repositoryBarbeiro.findByUsername(login.getUsername());

        if (barbeiro != null) {
            System.out.println("Barbeiro encontrado: " + barbeiro.getUsername());
        } else {
            System.out.println("Barbeiro não encontrado");
        }

        return barbeiro;
    }

    private List<Float> definirHorarioFuncionamento(Barbeiro barbeiro) {
        List<Float> horarios = new ArrayList<>();

        Float horarioAtual = barbeiro.getHorarioInicial();
        Float horarioFinal = barbeiro.getHorarioFinal();
        Float tempoPorCorte = barbeiro.getTempoPorCorte();

        while (horarioAtual < horarioFinal) {
            horarios.add(horarioAtual);

            horarioAtual += tempoPorCorte;

            horarioAtual = Math.round(horarioAtual * 100) / 100.0f;
        }

        return horarios;
    }

    private String formatarHorario(Float horario) {
        int horas = horario.intValue();
        int minutos = (int) ((horario - horas) * 60);
        return String.format("%02d:%02d", horas, minutos);
    }

    public void atribuirHorarioFuncionamento(Barbeiro barbeiro) {
        List<Float> horarios = definirHorarioFuncionamento(barbeiro);
        barbeiro.setHorariosDeFuncionamento(horarios);
        System.out.println("Horario atribuido com sucesso!");
    }

    public Barbeiro buscarBarbeiroPorUsername(String username) {
        Barbeiro barbeiro = repositoryBarbeiro.findByUsername(username);

        if (barbeiro == null) {
            throw new UsernameNotFoundException("Barbeiro com username '" + username + "' não encontrado.");
        }

        return barbeiro;
    }

    public void criarBarbeiro(Barbeiro barbeiro) {
        if (!barbeiro.getPassword().startsWith("$2a$")) {
            String senhaCodificada = passwordEncoder.encode(barbeiro.getPassword());
            barbeiro.setPassword(senhaCodificada);
        }
        repositoryBarbeiro.save(barbeiro);
    }

    public void removerBarbeiro(Barbeiro barbeiro){
        repositoryBarbeiro.delete(barbeiro);
    }

    public List<Corte> cortesDoBarbeiro(){
        Barbeiro barbeiro = buscarBarbeiroPorUsername("barbeiro");
        return barbeiro.getCortes();
    }

    //    public Corte cancelarCorte(){ serviceCorte.removerCorte;}
}
