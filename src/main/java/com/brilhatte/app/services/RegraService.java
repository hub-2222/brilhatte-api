package com.brilhatte.app.services;

import com.brilhatte.app.common.AbstractService;
import com.brilhatte.app.dtos.PedraVinculadaDTO;
import com.brilhatte.app.infra.exception.BusinessException;
import com.brilhatte.app.models.Pedra;
import com.brilhatte.app.models.QRegra;
import com.brilhatte.app.models.Regra;
import com.brilhatte.app.models.Roupa;
import com.brilhatte.app.repositories.RegraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Service
public class RegraService extends AbstractService<Regra, Long> {

    @Autowired
    private RegraRepository regraRepository;

    @Autowired
    private PedraService pedraService;

    @Override
    protected JpaRepository<Regra, Long> getRepository() {
        return regraRepository;
    }

    public List<Regra> findAllByRoupaId(Long roupaId) {
        return regraRepository.findAll(QRegra.regra.roupa.id.eq(roupaId));
    }

    public void create(Roupa roupa, List<PedraVinculadaDTO> listPedras) {
        List<Regra> listRegras = listPedras.stream().map(pedra -> {
            Regra regra = new Regra();
            regra.setRoupa(roupa);
            Pedra pedraEntity = pedraService.getById(pedra.getId());

            if (Objects.isNull(pedraEntity)) {
                throw new BusinessException("Pedra não encontrada");
            }

            regra.setPedra(pedraEntity);
            regra.setQuantidade(pedra.getQuantidade());
            return regra;
        }).toList();

        regraRepository.saveAll(listRegras);
    }

    public void update(Roupa roupa, List<PedraVinculadaDTO> listPedras) {
        List<Regra> existingRegras = findAllByRoupaId(roupa.getId());

        List<Regra> removedObjects = existingRegras.stream()
                .filter(existingRegra -> listPedras.stream().noneMatch(pedra -> pedra.getId().equals(existingRegra.getPedra().getId())))
                .toList();

        List<Regra> updatedObjects = listPedras.stream()
                .filter(pedra -> existingRegras.stream().anyMatch(existingRegra -> pedra.getId().equals(existingRegra.getPedra().getId())))
                .map(pedra -> {
                    Regra existingRegra = existingRegras.stream().filter(regra -> regra.getPedra().getId().equals(pedra.getId())).findFirst().get();
                    existingRegra.setQuantidade(pedra.getQuantidade());
                    return existingRegra;
                }).toList();

        List<Regra> newObjects = listPedras.stream()
                .filter(pedra -> Objects.isNull(pedra.getIdRegra()) &&
                                     Objects.nonNull(pedra.getId()) &&
                                    updatedObjects.stream().noneMatch(pedrasAtualizadas -> pedrasAtualizadas.getPedra().getId().equals(pedra.getId())))
                .map(pedra -> {
                    Regra regra = new Regra();
                    regra.setRoupa(roupa);
                    regra.setPedra(pedraService.getById(pedra.getId()));
                    regra.setQuantidade(pedra.getQuantidade());
                    return regra;
                }).toList();

        List<Regra> regrasToSave = Stream.of(updatedObjects, newObjects).flatMap(List::stream).toList();

        regraRepository.saveAll(regrasToSave);
        regraRepository.deleteAll(removedObjects);
    }

    public void deleteByRoupaId(Long id) {
        regraRepository.deleteAll(findAllByRoupaId(id));
    }
}
