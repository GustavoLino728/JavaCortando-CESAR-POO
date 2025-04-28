package com.javaCortando.poo.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "barbeiros")
public class Barbeiro extends Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    private List<Float> horariosDeFuncionamento;

    @Column(name ="horarioInicial", length = 5,  nullable = false)
    private Float horarioInicial;

    @Column(name ="horarioFinal",  length = 5,  nullable = false)
    private Float horarioFinal;

    @Column(name = "tempoPorCorte",  length = 5,  nullable = false)
    private Float tempoPorCorte;

    @OneToMany(mappedBy = "barbeiro")
    @JsonIgnoreProperties("barbeiro")
    private List<Corte> cortes;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "table_barbeiro_roles", joinColumns = @JoinColumn(name = "barbeiro_id"))
    @Column(name = "role_id")
    private List<String> roles = new ArrayList<>();

    @Override
    public String toString() {
        return "Barbeiro{" +
                "id=" + id +
                ", horariosDeFuncionamento=" + horariosDeFuncionamento +
                ", horarioInicial=" + horarioInicial +
                ", horarioFinal=" + horarioFinal +
                ", tempoPorCorte=" + tempoPorCorte +
                ", cortes=" + getHorariosCortes() +
                ", roles=" + roles +
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

    public List<Corte> getCortes() {
        return cortes;
    }

    public void setCortes(List<Corte> cortes) {
        this.cortes = cortes;
    }

    public List<Float> getHorariosCortes(){
        List<Float> horaCortes = new ArrayList<>();
        if(cortes != null){
            for(Corte corte : cortes){
                horaCortes.add(corte.getHorario());
            }
        }
        return horaCortes;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
