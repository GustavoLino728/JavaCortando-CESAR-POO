package com.javaCortando.poo.controller;

import com.javaCortando.poo.model.Barbeiro;
import com.javaCortando.poo.model.Cliente;
import com.javaCortando.poo.security.WebSecurityConfig;
import com.javaCortando.poo.service.ServiceBarbeiro;
import com.javaCortando.poo.service.ServiceCliente;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "geral", description = "Controlador para vizualizar dados com acesso para todos os usuários")
@SecurityRequirement(name = WebSecurityConfig.AUTHENTICATION_SCHEME)
public class ControllerGeral {
    @Autowired
    private ServiceCliente serviceCliente;

    @Autowired
    private ServiceBarbeiro serviceBarbeiro;

    /**.
     * @author Davi Mendes
     * @return ReponseEntity<List<Float>> - Lista com horários livres para marcar os cortes
     */
    @GetMapping("/home")
    @Operation(summary = "Mostra os horários dispoíveis para marcar corte com o barbeiro", description = "Método que retorna os horários")
    @ApiResponse(responseCode = "200", description = "horários listados com sucesso")
    @ApiResponse(responseCode = "401", description = "Usuário sem autorização para acessar esse método")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<List<Float>> home(){
        Barbeiro barbeiro = serviceBarbeiro.buscarBarbeiroPorUsername("barbeiro");
        List<Float> horarios = serviceCliente.listarHorarioDisponiveis(barbeiro);
        return ResponseEntity.ok().body(horarios);
    }


}
