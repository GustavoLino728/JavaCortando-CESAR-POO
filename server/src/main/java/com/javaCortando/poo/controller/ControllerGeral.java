package com.javaCortando.poo.controller;

import com.javaCortando.poo.model.Barbeiro;
import com.javaCortando.poo.model.Cliente;
import com.javaCortando.poo.service.ServiceBarbeiro;
import com.javaCortando.poo.service.ServiceCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ControllerGeral {
    @Autowired
    private ServiceCliente serviceCliente;

    @Autowired
    private ServiceBarbeiro serviceBarbeiro;

    @GetMapping("/home")
    public ResponseEntity<List<Float>> home(){
        Barbeiro barbeiro = serviceBarbeiro.buscarBarbeiroPorId(1L);
        List<Float> horarios = serviceCliente.listarHorarioDisponiveis(barbeiro);
        return ResponseEntity.ok().body(horarios);
    }

    @PostMapping("/cadastro")
    public ResponseEntity<Cliente> cadastrarCliente(@RequestBody Cliente cliente){
        serviceCliente.criarCliente(cliente);
        return ResponseEntity.ok().body(cliente);
    }
}
