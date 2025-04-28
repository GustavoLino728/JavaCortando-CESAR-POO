package com.javaCortando.poo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "cortes")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Corte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    @JsonIgnoreProperties("cortes")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "barbeiro_id")
    @JsonIgnoreProperties("cortes")
    private Barbeiro barbeiro;

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
                "id=" + id +
                ", cliente=" + cliente +
                ", barbeiro=" + barbeiro +
                ", data=" + data +
                ", horario=" + horario +
                ", preco=" + preco +
                ", cortesStatus=" + cortesStatus +
                '}';
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Barbeiro getBarbeiro() {
        return barbeiro;
    }

    public void setBarbeiro(Barbeiro barbeiro) {
        this.barbeiro = barbeiro;
    }
}
