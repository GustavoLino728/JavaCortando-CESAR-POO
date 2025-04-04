package com.javaCortando.poo.controller;

import com.javaCortando.poo.model.Barbeiro;
import com.javaCortando.poo.model.Cliente;
import com.javaCortando.poo.model.Corte;
import com.javaCortando.poo.service.ServiceCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ControllerCliente {
    @Autowired
    private ServiceCliente serviceCliente;

    @Autowired
    private Barbeiro defaultBarbeiro;

    @PostMapping("/cadastrar")
    public ResponseEntity<Cliente> cadastrarCliente(@RequestBody Cliente cliente){
        serviceCliente.criarCliente(cliente);
        return ResponseEntity.ok().body(cliente);
    }

//    @PostMapping("/login")
//    public ResponseEntity<Cliente> loginCliente(@RequestBody Cliente cliente){
//        login cliente
//        return ResponseEntity.ok().body(cliente);
//    }

    @PostMapping("/agendar")
    public ResponseEntity<Corte> salvarCorte(@RequestBody Corte corte){
        serviceCliente.salvarCorte(corte);
        return ResponseEntity.ok().body(corte);
    }

    @PostMapping("/cancelar-corte")
    public ResponseEntity<Corte> cancelarCorte(@RequestBody Corte corte){
        serviceCliente.cancelarCorte(corte);
        return ResponseEntity.ok().body(corte);
    }

    @GetMapping("/home")
    public ResponseEntity<List<Float>> home(){
        List<Float> horarios = serviceCliente.listarHorarioDisponiveis(defaultBarbeiro);
        return ResponseEntity.ok().body(horarios);
    }

//    public ResponseEntity<Corte> verCortesMarcados(@RequestBody Corte corte){
//        // listar meus cortes
//    }
//    private void verPerfil(){}







}
