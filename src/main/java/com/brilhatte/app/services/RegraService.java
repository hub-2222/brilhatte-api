package com.brilhatte.app.services;

import com.brilhatte.app.common.AbstractService;
import com.brilhatte.app.models.QRegra;
import com.brilhatte.app.models.Regra;
import com.brilhatte.app.repositories.RegraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegraService extends AbstractService<Regra, Long> {
    @Autowired
    private RegraRepository regraRepository;

    @Override
    protected JpaRepository<Regra, Long> getRepository() {
        return regraRepository;
    }

    public List<Regra> findAllByRoupaId(Long roupaId) {
        return regraRepository.findAll(QRegra.regra.roupa.id.eq(roupaId));
    }
}
