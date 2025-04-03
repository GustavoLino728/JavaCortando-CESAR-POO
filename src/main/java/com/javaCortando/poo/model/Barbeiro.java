package com.javaCortando.poo.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "barbeiros")
public class Barbeiro extends Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "horariosDeFuncionamento")
    @ElementCollection
    private List<Float> horariosDeFuncionamento;

    @Column(name ="horarioInicial", length = 5,  nullable = false)
    private Float horarioInicial;

    @Column(name ="horarioFinal",  length = 5,  nullable = false)
    private Float horarioFinal;

    @Column(name = "tempoPorCorte",  length = 5,  nullable = false)
    private Float tempoPorCorte;

    @Override
    public String toString() {
        return "Barbeiro{" +
                "id=" + id +
                ", horariosDeFuncionamento=" + horariosDeFuncionamento +
                ", horarioInicial=" + horarioInicial +
                ", horarioFinal=" + horarioFinal +
                ", tempoPorCorte=" + tempoPorCorte +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Float> getHorariosDeFuncionamento() {
        return horariosDeFuncionamento;
    }

    public void setHorariosDeFuncionamento(List<Float> horariosDeFuncionamento) {
        this.horariosDeFuncionamento = horariosDeFuncionamento;
    }

    public Float getHorarioInicial() {
        return horarioInicial;
    }

    public void setHorarioInicial(Float horarioInicial) {
        this.horarioInicial = horarioInicial;
    }

    public Float getTempoPorCorte() {
        return tempoPorCorte;
    }

    public void setTempoPorCorte(Float tempoPorCorte) {
        this.tempoPorCorte = tempoPorCorte;
    }

    public Float getHorarioFinal() {
        return horarioFinal;
    }

    public void setHorarioFinal(Float horarioFinal) {
        this.horarioFinal = horarioFinal;
    }
}
