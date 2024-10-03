package com.brilhatte.app.services;

import com.brilhatte.app.common.AbstractService;
import com.brilhatte.app.models.Roupa;
import com.brilhatte.app.repositories.RoupaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class RoupaService extends AbstractService<Roupa, Long> {

    @Autowired
    private RoupaRepository roupaRepository;


    @Override
    protected JpaRepository<Roupa, Long> getRepository() {
        return roupaRepository;
    }

    public Page<Roupa> findAllByRoupaId(Pageable pageable) {
        return this.roupaRepository.findAll(pageable);
    }
}
