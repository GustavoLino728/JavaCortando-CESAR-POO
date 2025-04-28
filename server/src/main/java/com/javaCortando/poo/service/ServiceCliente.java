package com.javaCortando.poo.service;

import com.javaCortando.poo.component.AlterarStatusCorte;
import com.javaCortando.poo.component.ListarHorarioDisponiveisComponent;
import com.javaCortando.poo.dto.Login;
import com.javaCortando.poo.model.Barbeiro;
import com.javaCortando.poo.model.Cliente;
import com.javaCortando.poo.model.Corte;
import com.javaCortando.poo.repository.RepositoryBarbeiro;
import com.javaCortando.poo.repository.RepositoryCliente;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
public class ServiceCliente {
    private static final Logger logger = LoggerFactory.getLogger(ServiceCliente.class);

    @Autowired
    private RepositoryCliente repositoryCliente;

    @Autowired
    private RepositoryBarbeiro repositoryBarbeiro;

    @Autowired
    private ServiceCorte serviceCorte;

    @Autowired
    private ServiceBarbeiro serviceBarbeiro;

    @Autowired
    private ListarHorarioDisponiveisComponent listarHorarioDisponiveisComponent;

    @Autowired
    private AlterarStatusCorte alterarStatusCorte;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Cliente findByUsername(Login login) {
        if (login == null || login.getUsername() == null) {
            return null;
        }

        System.out.println("Buscando cliente com username: " + login.getUsername());

        Cliente cliente = repositoryCliente.findByUsername(login.getUsername());

        if (cliente != null) {
            System.out.println("Cliente encontrado: " + cliente.getUsername());
        } else {
            System.out.println("Cliente não encontrado");
        }

        return cliente;
    }

    // auxiliar
    private Barbeiro getDefaultBarbeiro() {
        return repositoryBarbeiro.findByUsername("barbeiro");
    }

    //private Barbeiro getDefaultBarbeiro() {
    //        return serviceBarbeiro.buscarBarbeiroPorId(1L);
    //    }

    public void criarCliente(Cliente cliente){
        String encryptedPassword = passwordEncoder.encode(cliente.getPassword());
        cliente.setPassword(encryptedPassword);

        repositoryCliente.save(cliente);
    }

    private Cliente getAuthenticatedCliente() {
        org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            logger.error("Nenhuma autenticação encontrada no contexto de segurança");
            throw new RuntimeException("Usuário não autenticado");
        }

        String username = authentication.getName();
        logger.info("Tentando obter usuário autenticado: {}", username);

        if (username.equals("anonymousUser")) {
            logger.error("Usuário anônimo detectado, não é possível obter usuário autenticado");
            throw new RuntimeException("Usuário não autenticado");
        }

        Cliente user = repositoryCliente.findByUsername(username);

        if (user == null) {
            logger.error("Usuário não encontrado no banco de dados: {}", username);
            throw new RuntimeException("Usuário não encontrado: " + username);
        }

        logger.info("Usuário autenticado encontrado: {}", user.getUsername());
        return user;
    }

    public void salvarCorte(Corte corte) {
        Cliente clienteAutenticado = getAuthenticatedCliente();

        corte.setCliente(clienteAutenticado);

        Barbeiro defaultBarbeiro = getDefaultBarbeiro(); // pega o barbeiro ja criado
        corte.setBarbeiro(defaultBarbeiro);

        List<Float> horariosDisponiveis = listarHorarioDisponiveis(defaultBarbeiro);
        Float horarioSolicitado = corte.getHorario();

        if(corte.getData().isBefore(LocalDate.now())){
            throw new RuntimeException("Data menor que a data atual");
        }

        if (horariosDisponiveis.contains(horarioSolicitado)) {
            horariosDisponiveis.remove(horarioSolicitado);
            defaultBarbeiro.setHorariosDeFuncionamento(horariosDisponiveis);

            serviceBarbeiro.criarBarbeiro(defaultBarbeiro); // atualizar

            alterarStatusCorte.alteracaoGeralCorte(corte);

            serviceCorte.criarCorte(corte);

            logger.info("Corte agendado com sucesso para o horário: {}", horarioSolicitado);
        } else {
            logger.error("Horário indisponível: {}", horarioSolicitado);
            throw new RuntimeException("Horário indisponível: " + horarioSolicitado);
        }
    }

    public void cancelarCorte(Corte corte){

        Cliente clienteAutenticado = getAuthenticatedCliente();
        Barbeiro defaultBarbeiro = getDefaultBarbeiro();

        if (corte.getCliente() == null) {
            throw new RuntimeException("Corte não possui cliente associado");
        }

        Float horarioCancelado = corte.getHorario();
        List<Float> horariosDisponiveis = listarHorarioDisponiveis(defaultBarbeiro);
        if(corte.getCliente().equals(clienteAutenticado)){
            if (!horariosDisponiveis.contains(horarioCancelado)) {
                horariosDisponiveis.add(horarioCancelado);
                Collections.sort(horariosDisponiveis);
                defaultBarbeiro.setHorariosDeFuncionamento(horariosDisponiveis);
                serviceBarbeiro.criarBarbeiro(defaultBarbeiro); // atualizar, serve para modificar o horarioDeFuncionamento

                //alterarStatusCorte.alteracaoGeralCorte(corte);

                serviceCorte.removerCorte(corte);

                System.out.println("Corte cancelado com sucesso para o horário: " + horarioCancelado);
            } else {
                System.out.println("Usuário " + clienteAutenticado + " encontrado no banco de dados mas não teve o corte cancelado");
            }
        } else {
            System.out.println("Usuário " + clienteAutenticado + " não encontrado no banco de dados");
        }

    }

    public List<Corte> listarCortesPorClienteAutenticado() {
        Cliente clienteAutenticado = getAuthenticatedCliente();
        return clienteAutenticado.getCortes();
    }

    public List<Float> listarHorarioDisponiveis(Barbeiro barbeiro){
        return listarHorarioDisponiveisComponent.listarHorarioDisponiveis(barbeiro);
    }
}
