package com.javaCortando.poo.component;

import com.javaCortando.poo.model.Barbeiro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListarHorarioDisponiveisComponent {

    public List<String> pegarHorarios(Barbeiro barbeiro){
        return barbeiro.getHorariosDeFuncionamento();
    }

    public List<String> listarHorarioDisponiveis(Barbeiro barbeiro){
        return pegarHorarios(barbeiro);
    }
}
