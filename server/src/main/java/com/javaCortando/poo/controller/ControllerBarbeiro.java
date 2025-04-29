package com.javaCortando.poo.controller;

import com.javaCortando.poo.dto.CorteDTO;
import com.javaCortando.poo.model.Corte;
import com.javaCortando.poo.security.WebSecurityConfig;
import com.javaCortando.poo.service.ServiceBarbeiro;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/barbeiro")
@Tag(name = "barbeiro", description = "Controlador para operações com o barbeiro")
@SecurityRequirement(name = WebSecurityConfig.AUTHENTICATION_SCHEME)
public class ControllerBarbeiro {
    @Autowired
    private ServiceBarbeiro serviceBarbeiro;

//    public ResponseEntity<Corte> cancelarCorte(Corte corte) {
//        serviceBarbeiro.cancelarCorte(corte);
//        return ResponseEntity.ok().body(corte);
//    }

    /**.
     * @author Davi Mendes
     * @return  ResponseEntity<List<CorteDTO>> - cortes marcados, os cortes estão em dto
     */
    @GetMapping("/ver-cortes")
    @Operation(summary = "Mostra os horários marcados", description = "Mostra apenas o Horario e o dia dos cortes")
    @ApiResponse(responseCode = "200", description = "horários marcados listados com sucesso")
    @ApiResponse(responseCode = "401", description = "Usuário sem autorização para acessar esse método")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<List<CorteDTO>> buscarCortePorBarbeiro(){
        List<Corte> cortes = serviceBarbeiro.cortesDoBarbeiro();

        List<CorteDTO> cortesDTO = cortes.stream()
                                         .map(CorteDTO::new)
                                         .collect(Collectors.toList());

        return ResponseEntity.ok().body(cortesDTO);
    }
}
