package com.brilhatte.app.services;

import com.brilhatte.app.common.AbstractService;
import com.brilhatte.app.models.Pedra;
import com.brilhatte.app.repositories.PedraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class PedraService extends AbstractService<Pedra, Long> {

    @Autowired
    private PedraRepository pedraRepository;

    @Override
    protected JpaRepository<Pedra, Long> getRepository() {
        return pedraRepository;
    }
}
