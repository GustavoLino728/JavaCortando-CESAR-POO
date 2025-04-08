package com.javaCortando.poo.init;

import com.javaCortando.poo.service.ServiceBarbeiro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StartApp implements CommandLineRunner {

    @Autowired
    private ServiceBarbeiro serviceBarbeiro;

    public static void main(String[] args) {
        SpringApplication.run(StartApp.class, args);
    }

    @Override
    public void run(String... args) {
        serviceBarbeiro.salvarBarbeiroInicial();
    }
}