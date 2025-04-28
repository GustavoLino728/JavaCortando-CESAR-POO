package com.javaCortando.poo.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", length = 50,  nullable = false)
    private String username;

    @Column(name = "telefone",  length = 11,  nullable = false)
    private String telefone;

    @Column(name = "email",  length = 50,  nullable = false)
    private String email;

    @Column(name = "password",   length = 200,  nullable = false)
    private String password;

    @Override
    public String toString() {
        return "Usuario{" +
                ", nome='" + username + '\'' +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + password + '\'' +
                '}';
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String nome) {
        this.username = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String senha) {
        this.password = senha;
    }
}
