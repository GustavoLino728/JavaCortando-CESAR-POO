package com.javaCortando.poo.component;

import com.javaCortando.poo.model.Corte;
import com.javaCortando.poo.repository.RepositoryCortes;
import com.javaCortando.poo.service.ServiceCorte;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class RemoverCortesAntigos {
    @Autowired
    private RepositoryCortes repositoryCortes;

    @Transactional
    public void limparCortesAntigos() {
        LocalDate hoje = LocalDate.now();

        List<Corte> cortesAntigos = repositoryCortes.findByDataBeforeOrDataIsNull(hoje);

        if (!cortesAntigos.isEmpty()) {
            repositoryCortes.deleteAllInBatch(cortesAntigos);
            System.out.println("Removidos " + cortesAntigos + " cortes antigos");
        }
    }
}
