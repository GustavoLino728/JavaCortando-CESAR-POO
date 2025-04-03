package com.javaCortando.poo.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "cortes")
public class Corte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nomeCliente")
    private String nomeCliente;

    @Column(name = "data")
    private LocalDate data;

    @Column(name = "horario")
    private Float horario;

    @Column(name = "preco")
    private Float preco;

    @Column(name = "cortesStatus")
    private boolean cortesStatus;

    @Override
    public String toString() {
        return "Corte{" +
                "nomeCliente='" + nomeCliente + '\'' +
                ", data=" + data +
                ", horario=" + horario +
                ", preco=" + preco +
                ", cortesStatus=" + cortesStatus +
                '}';
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
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
