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

    public void salvarBarbeiro(Barbeiro barbeiro){
        repositoryBarbeiro.save(barbeiro);
    }

}
