package com.javaCortando.poo.controller;

import com.javaCortando.poo.dto.AgendamentoRequestDTO;
import com.javaCortando.poo.dto.CorteDTO;
import com.javaCortando.poo.model.Barbeiro;
import com.javaCortando.poo.model.Cliente;
import com.javaCortando.poo.model.Corte;
import com.javaCortando.poo.service.ServiceBarbeiro;
import com.javaCortando.poo.service.ServiceCliente;
import com.javaCortando.poo.service.ServiceCorte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cliente")
public class ControllerCliente {
    @Autowired
    private ServiceCliente serviceCliente;

    @Autowired
    private ServiceBarbeiro serviceBarbeiro;

    @Autowired
    private ServiceCorte serviceCorte;

    @PostMapping(value = "/marcar", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CorteDTO> salvarCorte(@RequestBody AgendamentoRequestDTO request) {
        if (request.getHorario() == null || request.getData() == null) {
            return ResponseEntity.badRequest().build();
        }

        Corte corte = new Corte();
        corte.setHorario(request.getHorario());
        corte.setData(request.getData());

        serviceCliente.salvarCorte(corte);
        return ResponseEntity.ok().body(new CorteDTO(corte));
    }

    @PostMapping("/desmarcar")
    public ResponseEntity<CorteDTO> cancelarCorte(@RequestBody AgendamentoRequestDTO request) {
        if (request.getHorario() == null || request.getData() == null) {
            return ResponseEntity.badRequest().build();
        }

        Corte corte = serviceCorte.findByDataAndHorario(request.getData(), request.getHorario());
        serviceCliente.cancelarCorte(corte);

        return ResponseEntity.ok().body(new CorteDTO(corte));
    }

    @GetMapping("/meus-cortes")
    public ResponseEntity<List<CorteDTO>> listarMeusCortes() {
        List<Corte> cortes = serviceCliente.listarCortesPorClienteAutenticado();

        List<CorteDTO> cortesDTO = cortes.stream()
                                         .map(CorteDTO::new)
                                         .collect(Collectors.toList());

        return ResponseEntity.ok().body(cortesDTO);
    }

//    private void verPerfil(){}

}
