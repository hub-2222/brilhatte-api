package com.brilhatte.app.services.calculo;

import com.brilhatte.app.common.AbstractService;
import com.brilhatte.app.models.QPedra;
import com.brilhatte.app.models.calculo.Calculo;
import com.brilhatte.app.models.calculo.QCalculo;
import com.brilhatte.app.repositories.calculo.CalculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CalculoService extends AbstractService<Calculo, Long> {

    private static final QCalculo qCalculo = QCalculo.calculo;

    @Autowired
    private CalculoRepository calculoRepository;

    @Autowired
    private HotfixService hotfixService;

    @Override
    protected JpaRepository<Calculo, Long> getRepository() {
        return calculoRepository;
    }

    public Calculo findByRoupaId(Long roupaId) {
        return calculoRepository.findOne(qCalculo.roupa.id.eq(roupaId)).orElse(null);
    }

    @Override
    public Calculo save(Calculo entity) {
        Calculo calculo = findByRoupaId(entity.getRoupa().getId());
        if (calculo != null) {
            entity.setId(calculo.getId());
        }
        return super.save(entity);
    }

    public void deleteByRoupaId(Long roupaId) {
        Calculo calculo = findByRoupaId(roupaId);
        hotfixService.deleteByCalculoId(calculo.getId());
        calculoRepository.delete(findByRoupaId(roupaId));
    }
}
