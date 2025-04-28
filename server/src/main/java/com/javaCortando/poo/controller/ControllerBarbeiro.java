package com.javaCortando.poo.controller;

import com.javaCortando.poo.dto.CorteDTO;
import com.javaCortando.poo.model.Corte;
import com.javaCortando.poo.service.ServiceBarbeiro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/barbeiro")
public class ControllerBarbeiro {
    @Autowired
    private ServiceBarbeiro serviceBarbeiro;

//    public ResponseEntity<Corte> cancelarCorte(Corte corte) {
//        serviceBarbeiro.cancelarCorte(corte);
//        return ResponseEntity.ok().body(corte);
//    }

    @GetMapping("/ver-cortes")
    public ResponseEntity<List<CorteDTO>> buscarCortePorBarbeiro(){
        List<Corte> cortes = serviceBarbeiro.cortesDoBarbeiro();

        List<CorteDTO> cortesDTO = cortes.stream()
                                         .map(CorteDTO::new)
                                         .collect(Collectors.toList());

        return ResponseEntity.ok().body(cortesDTO);
    }
}
