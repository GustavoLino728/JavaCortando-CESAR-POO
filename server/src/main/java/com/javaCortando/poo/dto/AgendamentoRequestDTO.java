package com.javaCortando.poo.dto;

import java.time.LocalDate;

public class AgendamentoRequestDTO {
    private LocalDate data;
    private Float horario;

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
}
