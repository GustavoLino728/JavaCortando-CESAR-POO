package com.javaCortando.poo.component;

import com.javaCortando.poo.model.Corte;
import com.javaCortando.poo.repository.RepositoryCortes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class AlterarStatusCorte {

    private static final float VALOR_CORTE = 25.0f;

    @Autowired
    private RepositoryCortes repositoryCortes;

    private void alterarStatusCorte(Corte corte){
        corte.setCortesStatus(!corte.isCortesStatus());
    }

    private void alterarStatusPreco(Corte corte){
        corte.setPreco(corte.getPreco() == null ? VALOR_CORTE : null);
    }

    public void alteracaoGeralCorte(Corte corte){
        alterarStatusCorte(corte);
        alterarStatusPreco(corte);

    }

}
