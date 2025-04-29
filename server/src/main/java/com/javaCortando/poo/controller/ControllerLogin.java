package com.javaCortando.poo.controller;

import com.javaCortando.poo.dto.Login;
import com.javaCortando.poo.dto.Sessao;
import com.javaCortando.poo.model.Barbeiro;
import com.javaCortando.poo.model.Cliente;
import com.javaCortando.poo.repository.RepositoryBarbeiro;
import com.javaCortando.poo.repository.RepositoryCliente;
import com.javaCortando.poo.security.SecurityConfig;
import com.javaCortando.poo.security.WebSecurityConfig;
import com.javaCortando.poo.security.jwt.JWTCreator;
import com.javaCortando.poo.security.jwt.JWTObject;
import com.javaCortando.poo.service.ServiceBarbeiro;
import com.javaCortando.poo.service.ServiceCliente;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@Tag(name = "login", description = "Controlador para realizar apenas os logins")
@SecurityRequirement(name = WebSecurityConfig.AUTHENTICATION_SCHEME)
public class ControllerLogin {
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private SecurityConfig securityConfig;
    @Autowired
    private ServiceCliente serviceCliente;
    @Autowired
    private ServiceBarbeiro serviceBarbeiro;

    /**.
     * @author Davi Mendes
     * @param login - nome de usuário e a senha, para cliente, esta como um dto
     * @return Sessao - Token para requisições
     */
    @PostMapping("/login-cliente")
    @Operation(summary = "Login do cliente", description = "Gerar o token do cliente para as proximas requisições")
    @ApiResponse(responseCode = "200", description = "Token gerado e login executado")
    @ApiResponse(responseCode = "403", description = "Autorizado mas algo esta errado com o body da requisição")
    @ApiResponse(responseCode = "400", description = "Algo de errado na requisição")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public Sessao logar(@RequestBody Login login){
        Cliente cliente = serviceCliente.findByUsername(login);
        if(cliente!=null) {
            SecurityContextHolder.clearContext();

            boolean passwordOk = encoder.matches(login.getPassword(), cliente.getPassword());
            System.out.println("Cliente login attempt: " + login.getUsername());
            System.out.println("Password match result: " + passwordOk);

            if (!passwordOk) {
                throw new RuntimeException("Senha inválida para o login: " + login.getUsername());
            }
            //Estamos enviando um objeto Sessão para retornar mais informações do usuário
            Sessao sessao = new Sessao();
            sessao.setLogin(cliente.getUsername());

            JWTObject jwtObject = new JWTObject();
            jwtObject.setSubject(cliente.getUsername());
            jwtObject.setIssueedAT(new Date(System.currentTimeMillis()));
            jwtObject.setExpiration((new Date(System.currentTimeMillis() + SecurityConfig.EXPIRATION)));
            //if(jwtObject.setExpiration()) verificação se expirar ele avisa
            jwtObject.setRoles(cliente.getRoles());
            sessao.setToken(JWTCreator.create(SecurityConfig.PREFIX, SecurityConfig.KEY, jwtObject));
            return sessao;
        }else {
            throw new RuntimeException("Erro ao tentar fazer login");
        }
    }

    /**.
     * @author Davi Mendes
     * @param login - nome de usuário e a senha, para barbeiro, esta como um dto
     * @return Sessao - Token para requisições
     */
    @PostMapping("/login-barbeiro")
    @Operation(summary = "Login do barbeiro", description = "Gerar o token do barbeiro para as proximas requisições")
    @ApiResponse(responseCode = "200", description = "Token gerado e login executado")
    @ApiResponse(responseCode = "403", description = "Autorizado mas algo esta errado com o body da requisição")
    @ApiResponse(responseCode = "400", description = "Algo de errado na requisição")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public Sessao logarManager(@RequestBody Login login){
        Barbeiro barbeiro = serviceBarbeiro.findByUsername(login);
        if(barbeiro!=null) {

            SecurityContextHolder.clearContext();

            // sout para ver a senha que tem salva decodificada (sem codificação)
            // sout para ver a senha que tem salva códificada
            // sout para ver a senha que estou colocando decodificada (sem codificação)
            // sout para ver a senha que estou colocando codificada

            boolean passwordOk = encoder.matches(login.getPassword(), barbeiro.getPassword());
            System.out.println("Barbeiro login attempt: " + login.getUsername());
            System.out.println("Password match result: " + passwordOk);
            System.out.println("Raw password: " + login.getPassword());
            System.out.println("Encoded password: " + barbeiro.getPassword());

            if (!passwordOk) {
                throw new RuntimeException("Senha inválida para o login: " + login.getUsername());
            }
            //Estamos enviando um objeto Sessão para retornar mais informações do usuário
            Sessao sessao = new Sessao();
            sessao.setLogin(barbeiro.getUsername());

            JWTObject jwtObject = new JWTObject();
            jwtObject.setSubject(barbeiro.getUsername());
            jwtObject.setIssueedAT(new Date(System.currentTimeMillis()));
            jwtObject.setExpiration((new Date(System.currentTimeMillis() + SecurityConfig.EXPIRATION)));
            jwtObject.setRoles(barbeiro.getRoles());
            sessao.setToken(JWTCreator.create(SecurityConfig.PREFIX, SecurityConfig.KEY, jwtObject));
            return sessao;
        }else {
            throw new RuntimeException("Erro ao tentar fazer login");
        }
    }
}