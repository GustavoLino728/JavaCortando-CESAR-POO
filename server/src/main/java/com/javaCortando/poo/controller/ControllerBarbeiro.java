package com.javaCortando.poo.controller;

import com.javaCortando.poo.model.Corte;
import com.javaCortando.poo.service.ServiceBarbeiro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/barbeiro")
public class ControllerBarbeiro {
    @Autowired
    private ServiceBarbeiro serviceBarbeiro;

    //@PostMapping("/login")
//    public ResponseEntity<Barbeiro> loginBarbeiro(@RequestBody Barbeiro barbeiro){
//        login barbeiro
//        return ResponseEntity.ok().body(barbeiro);
//    }

//    public ResponseEntity<Corte> cancelarCorte(Corte corte) {
//        serviceBarbeiro.cancelarCorte(corte);
//        return ResponseEntity.ok().body(corte);
//    }

//    public ResponseEntity<List<Corte>> buscarCortePorBarbeiro(Barbeiro barbeiro){
//        serviceBarbeiro.buscarCortePorBarbeiro(barbeiro);
//    }
}
