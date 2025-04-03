package com.javaCortando.poo.model;

import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;

@Entity
@Table
public class Barbeiro {

    @Id
    @Column(name ="horarioInicial")
    private Float horarioInicial;


    @Column(name ="horarioFinal")
    private Float horarioFinal;

    @Column(name = "tempoPorCorte")
    private Float tempoPorCorte;

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
