package com.javaCortando.poo.model;

import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;

import java.time.LocalDateTime;

@Entity
@Table
public class Corte {

    @Id
    @Column(name = "nome")
    private String nome;

    @Column(name = "data")
    private LocalDateTime data;

    @Column(name = "horario")
    private Float horario;

    @Column(name = "preco")
    private Float preco;

    @Column(name = "cortesStatus")
    private boolean cortesStatus;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Float getHorario() {
        return horario;
    }

    public void setHorario(Float horario) {
        this.horario = horario;
    }

    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }

    public boolean isCortesStatus() {
        return cortesStatus;
    }

    public void setCortesStatus(boolean cortesStatus) {
        this.cortesStatus = cortesStatus;
    }
}
