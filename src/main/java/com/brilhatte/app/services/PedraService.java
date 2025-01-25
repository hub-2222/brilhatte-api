package com.brilhatte.app.services;

import com.brilhatte.app.common.AbstractService;
import com.brilhatte.app.models.Pedra;
import com.brilhatte.app.models.QPedra;
import com.brilhatte.app.repositories.PedraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class PedraService extends AbstractService<Pedra, Long> {

    private static final QPedra qPedra = QPedra.pedra;

    @Autowired
    private PedraRepository pedraRepository;

    @Override
    protected JpaRepository<Pedra, Long> getRepository() {
        return pedraRepository;
    }

    public Page<Pedra> findAll(Pageable pageable, String nome) {
        if (nome == null || nome.isEmpty()) {
            return pedraRepository.findAll(pageable);
        } else {
            return pedraRepository.findAll(qPedra.nome.likeIgnoreCase("%"+nome+"%"), pageable);
        }
    }
}
