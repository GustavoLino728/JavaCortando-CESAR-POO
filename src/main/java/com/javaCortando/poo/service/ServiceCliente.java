package com.javaCortando.poo.service;

import com.javaCortando.poo.component.ListarHorarioDisponiveisComponent;
import com.javaCortando.poo.model.Barbeiro;
import com.javaCortando.poo.model.Cliente;
import com.javaCortando.poo.model.Corte;
import com.javaCortando.poo.repository.RepositoryCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceCliente {
    @Autowired
    private RepositoryCliente repositoryCliente;

    @Autowired
    private ServiceCorte serviceCorte;

    @Autowired
    private ListarHorarioDisponiveisComponent listarHorarioDisponiveisComponent;

    public void criarCliente(Cliente cliente){
        repositoryCliente.save(cliente);
    }

    public void salvarCorte(Corte corte){
        serviceCorte.criarCorte(corte);
    }

    public void cancelarCorte(Corte corte){
        serviceCorte.removerCorte(corte);
    }

//    public List<Corte> listarMeusCortes(){}
//    public void verPerfil(Cliente cliente){}

    public List<Float> listarHorarioDisponiveis(Barbeiro barbeiro){
        return listarHorarioDisponiveisComponent.listarHorarioDisponiveis(barbeiro);
    }

}
