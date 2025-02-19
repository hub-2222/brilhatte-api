package com.brilhatte.app.services;

import com.brilhatte.app.common.AbstractService;
import com.brilhatte.app.models.Pedra;
import com.brilhatte.app.models.QRoupa;
import com.brilhatte.app.models.Roupa;
import com.brilhatte.app.repositories.RoupaRepository;
import com.brilhatte.app.utils.ImageUtils;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.DataFormatException;

@Service
public class RoupaService extends AbstractService<Roupa, Long> {

    private static final QRoupa qRoupa = QRoupa.roupa;

    @Autowired
    private RoupaRepository roupaRepository;


    @Override
    protected JpaRepository<Roupa, Long> getRepository() {
        return roupaRepository;
    }

    public Page<Roupa> findAllByNomeWithImage(Pageable pageable, String nome) {
        Predicate where;

        if (nome == null || nome.isEmpty()) {
            where = qRoupa.isNotNull();
        } else {
            where = qRoupa.nome.likeIgnoreCase("%"+nome+"%");
        }

        Page<Roupa> page = this.roupaRepository.findAll(where, pageable);

        return page;
    }

    public Roupa findById(Long id) {
        return roupaRepository.findById(id).orElse(null);
    }
}
