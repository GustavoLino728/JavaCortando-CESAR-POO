package com.javaCortando.poo.controller;

import com.javaCortando.poo.dto.AgendamentoRequestDTO;
import com.javaCortando.poo.dto.CorteDTO;
import com.javaCortando.poo.model.Barbeiro;
import com.javaCortando.poo.model.Cliente;
import com.javaCortando.poo.model.Corte;
import com.javaCortando.poo.security.WebSecurityConfig;
import com.javaCortando.poo.service.ServiceBarbeiro;
import com.javaCortando.poo.service.ServiceCliente;
import com.javaCortando.poo.service.ServiceCorte;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cliente")
@Tag(name = "cliente", description = "Controlador para métodos do cliente")
@SecurityRequirement(name = WebSecurityConfig.AUTHENTICATION_SCHEME)
public class ControllerCliente {
    @Autowired
    private ServiceCliente serviceCliente;

    @Autowired
    private ServiceBarbeiro serviceBarbeiro;

    @Autowired
    private ServiceCorte serviceCorte;

    /**.
     * @author Davi Mendes
     * @param request - dto que contem o horário e a data do corte
     * @return  ResponseEntity<CorteDTO> - dto do corte, informações mais limpas
     */
    @PostMapping(value = "/marcar", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Marcar corte", description = "Cria um novo corte apartir do dia e horário")
    @ApiResponse(responseCode = "201", description = "Corte marcado com sucesso")
    @ApiResponse(responseCode = "401", description = "Usuário sem autorização para acessar esse método")
    @ApiResponse(responseCode = "403", description = "Autorizado mas algo esta errado com o body da requisição")
    @ApiResponse(responseCode = "400", description = "Algo de errado na requisição")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
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

    /**.
     * @author Davi Mendes
     * @param request - dto que contem o horário e a data do corte
     * @return  ResponseEntity<CorteDTO> - dto do corte, informações mais limpas
     */
    @PostMapping("/desmarcar")
    @Operation(summary = "Desmarcar corte", description = "Deleta o corte apartir do dia e horário e reatribui esse horário a lista de horários livres do barbeiro")
    @ApiResponse(responseCode = "200", description = "Corte desmarcado com sucesso")
    @ApiResponse(responseCode = "401", description = "Usuário sem autorização para acessar esse método")
    @ApiResponse(responseCode = "403", description = "Autorizado mas algo esta errado com o body da requisição")
    @ApiResponse(responseCode = "400", description = "Algo de errado na requisição")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<CorteDTO> cancelarCorte(@RequestBody AgendamentoRequestDTO request) {
        if (request.getHorario() == null || request.getData() == null) {
            return ResponseEntity.badRequest().build();
        }

        Corte corte = serviceCorte.findByDataAndHorario(request.getData(), request.getHorario());
        serviceCliente.cancelarCorte(corte);

        return ResponseEntity.ok().body(new CorteDTO(corte));
    }

    /**.
     * @author Davi Mendes
     * @return  ResponseEntity<List<CorteDTO>> - lista de dto dos cortes marcadods
     */
    @GetMapping("/meus-cortes")
    @Operation(summary = "Ver cortes do cliente", description = "Faz a listagem de todos os cortes marcado pelo cliente autenticado")
    @ApiResponse(responseCode = "200", description = "Listagem dos cortes do cliente logado")
    @ApiResponse(responseCode = "401", description = "Usuário sem autorização para acessar esse método")
    @ApiResponse(responseCode = "403", description = "Autorizado mas algo esta errado com o body da requisição")
    @ApiResponse(responseCode = "400", description = "Algo de errado na requisição")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<List<CorteDTO>> listarMeusCortes() {
        List<Corte> cortes = serviceCliente.listarCortesPorClienteAutenticado();

        List<CorteDTO> cortesDTO = cortes.stream()
                                         .map(CorteDTO::new)
                                         .collect(Collectors.toList());

        return ResponseEntity.ok().body(cortesDTO);
    }


    /**.
     * @author Davi Mendes
     * @param cliente - cliente
     * @return  ResponseEntity<Cliente> - retorna um cliente
     */
    @PostMapping("/cadastro")
    @Operation(summary = "Salvar um cliente", description = "Cria um novo cliente")
    @ApiResponse(responseCode = "201", description = "Cliente salvo com sucesso")
    @ApiResponse(responseCode = "403", description = "Autorizado mas algo esta errado com o body da requisição")
    @ApiResponse(responseCode = "400", description = "Algo de errado na requisição")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<Cliente> cadastrarCliente(@RequestBody Cliente cliente){
        serviceCliente.criarCliente(cliente);
        return ResponseEntity.ok().body(cliente);
    }

//    private void verPerfil(){}

}
