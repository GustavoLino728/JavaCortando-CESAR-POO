package com.javaCortando.poo.service;

import com.javaCortando.poo.model.Corte;
import com.javaCortando.poo.repository.RepositoryCortes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ServiceCorte {
    @Autowired
    private RepositoryCortes repository;

    public Corte findByDataAndHorario(LocalDate data, Float horario) {
        return repository.findByDataAndHorario(data, horario);
    }

    public void criarCorte(Corte corte){
        repository.save(corte);
    }

    public void removerCorte(Corte corte){
        repository.delete(corte);
    }

//    public List<Corte> listarMeusCortes(){
//        //Ver os meus cortes
//    }

    public List<Corte> getCortes(){
        return repository.findAll();
    }

}
