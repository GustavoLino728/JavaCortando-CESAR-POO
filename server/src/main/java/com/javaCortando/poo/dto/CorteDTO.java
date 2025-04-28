package com.javaCortando.poo.dto;


import com.javaCortando.poo.model.Corte;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CorteDTO {
    private Long id;
    private String nomeCliente;
    private String nomeBarbeiro;
    private String data;
    private String horario;
    private Float preco;
    private boolean status;

    public CorteDTO() {
    }

    public CorteDTO(Corte corte) {
        this.id = corte.getId();
        this.nomeCliente = corte.getCliente() != null ? corte.getCliente().getUsername() : null;
        this.nomeBarbeiro = corte.getBarbeiro() != null ? corte.getBarbeiro().getUsername() : null;

        this.data = corte.getData() != null ?
                corte.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : null;

        if (corte.getHorario() != null) {
            int horas = corte.getHorario().intValue();
            int minutos = (int) ((corte.getHorario() - horas) * 60);
            this.horario = String.format("%02d:%02d", horas, minutos);
        }

        this.preco = corte.getPreco();
        this.status = corte.isCortesStatus();
    }

    public static CorteDTO fromEntity(Corte corte) {
        return new CorteDTO(corte);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getNomeBarbeiro() {
        return nomeBarbeiro;
    }

    public void setNomeBarbeiro(String nomeBarbeiro) {
        this.nomeBarbeiro = nomeBarbeiro;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
