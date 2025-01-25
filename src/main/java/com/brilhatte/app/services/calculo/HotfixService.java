package com.brilhatte.app.services.calculo;

import com.brilhatte.app.common.AbstractService;
import com.brilhatte.app.models.calculo.Hotfix;
import com.brilhatte.app.repositories.calculo.HotfixRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class HotfixService extends AbstractService<Hotfix, Long> {

    @Autowired
    private HotfixRepository hotfixRepository;

    @Override
    protected JpaRepository<Hotfix, Long> getRepository() {
        return hotfixRepository;
    }
}
