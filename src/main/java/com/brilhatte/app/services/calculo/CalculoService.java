package com.brilhatte.app.services.calculo;

import com.brilhatte.app.common.AbstractService;
import com.brilhatte.app.models.calculo.Calculo;
import com.brilhatte.app.repositories.calculo.CalculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CalculoService extends AbstractService<Calculo, Long> {

    @Autowired
    private CalculoRepository calculoRepository;

    @Override
    protected JpaRepository<Calculo, Long> getRepository() {
        return calculoRepository;
    }
}
