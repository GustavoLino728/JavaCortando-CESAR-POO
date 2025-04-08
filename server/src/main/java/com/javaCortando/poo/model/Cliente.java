package com.javaCortando.poo.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "clientes")
public class Cliente extends Usuario{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cortesAgendadosCliente")
    @ElementCollection
    private List<Float> cortesAgendados;

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", cortesAgendados=" + cortesAgendados +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Float> getCortesAgendados() {
        return cortesAgendados;
    }

    public void setCortesAgendados(List<Float> cortesAgendados) {
        this.cortesAgendados = cortesAgendados;
    }
}
