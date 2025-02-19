package com.brilhatte.app.services.calculo;

import com.brilhatte.app.common.AbstractService;
import com.brilhatte.app.models.calculo.Calculo;
import com.brilhatte.app.models.calculo.Hotfix;
import com.brilhatte.app.models.calculo.QHotfix;
import com.brilhatte.app.repositories.calculo.HotfixRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class HotfixService extends AbstractService<Hotfix, Long> {

    private static final QHotfix qHotfix = QHotfix.hotfix;

    @Autowired
    private HotfixRepository hotfixRepository;

    @Override
    protected JpaRepository<Hotfix, Long> getRepository() {
        return hotfixRepository;
    }

    public List<Hotfix> findAllByCalculoId(Long calculoId) {
        return hotfixRepository.findAll(qHotfix.calculo.id.eq(calculoId));
    }

    public void update(List<Hotfix> hotfixes, Calculo calculo) {
        List<Hotfix> existingHotfixes = findAllByCalculoId(calculo.getId());

        if (existingHotfixes.isEmpty()) {
            hotfixRepository.saveAll(hotfixes);
            return;
        }

        List<Hotfix> removedObjects = existingHotfixes.stream()
                .filter(existingHotfix -> hotfixes.stream().noneMatch(hotfix -> Objects.equals(hotfix.getId(), existingHotfix.getId())))
                .toList();

        List<Hotfix> updatedObjects = hotfixes.stream()
                .filter(hotfix -> existingHotfixes.stream().anyMatch(existingHotfix -> Objects.equals(hotfix.getId(),existingHotfix.getId())))
                .map(hotfix -> {
                    Hotfix existingHotfix = existingHotfixes.stream().filter(h -> h.getId().equals(hotfix.getId())).findFirst().get();
                    existingHotfix.setComprimentoUtilizado(hotfix.getComprimentoUtilizado());
                    existingHotfix.setLarguraUtilizada(hotfix.getLarguraUtilizada());
                    return existingHotfix;
                }).toList();

        List<Hotfix> newObjects = hotfixes.stream()
                .filter(hotfix -> Objects.isNull(hotfix.getId()))
                .toList();

        List<Hotfix> hotfixesToSave = List.of(updatedObjects, newObjects).stream().flatMap(List::stream).toList();

        hotfixRepository.deleteAll(removedObjects);
        hotfixRepository.saveAll(hotfixesToSave);
    }
}
