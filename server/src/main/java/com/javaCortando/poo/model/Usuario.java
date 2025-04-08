package com.javaCortando.poo.model;

import jakarta.persistence.*;

@MappedSuperclass
public class Usuario {

    @Column(name = "nome", length = 50,  nullable = false)
    private String nome;

    @Column(name = "telefone",  length = 11,  nullable = false)
    private String telefone;

    @Column(name = "email",  length = 50,  nullable = false)
    private String email;

    @Column(name = "senha",   length = 50,  nullable = false)
    private String senha;

    @Override
    public String toString() {
        return "Usuario{" +
                ", nome='" + nome + '\'' +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
