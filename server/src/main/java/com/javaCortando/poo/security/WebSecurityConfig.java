package com.javaCortando.poo.security;

import com.javaCortando.poo.security.jwt.JWTFilter;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@SecurityScheme(name = WebSecurityConfig.AUTHENTICATION_SCHEME, type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "bearer")
public class WebSecurityConfig {

    public static final String AUTHENTICATION_SCHEME = "bearerAuth";

    @Autowired
    private SecurityDatabaseService securityService;

    @Autowired
    private ConfigEncoder encoder;

    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(securityService).passwordEncoder(encoder.passwordEncoder());
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .headers(headers -> headers
                        .frameOptions(frameOptions -> frameOptions.sameOrigin())
                )
                .csrf(csrf -> csrf.disable())
                .cors(cors -> {}) // Habilita CORS sem configurações específicas
                .addFilterAfter(new JWTFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/login-cliente","/login-barbeiro", "/cliente/cadastro").permitAll()
                        .requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers("/home").hasAnyRole("USERS", "MANAGERS")
                        .requestMatchers("/cliente/agendar","/cliente/cancelar-corte").hasRole("USERS")
                        //.requestMatchers("/biblioteca/adicionar-livro").hasRole("MANAGERS")
                        .requestMatchers("/v3/api-docs/**", "swagger-ui/**", "swagger-ui.html").permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        //.httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
