package com.brilhatte.app.common;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public abstract class AbstractService<T extends BaseEntity, ID> implements BaseService<T, Long> {

    protected abstract JpaRepository<T, Long> getRepository();

    @Override
    public Iterable<T> getAll() {
        return getRepository().findAll();
    }

    @Override
    public T getById(Long id) {
        return getRepository()
                .findById(id)
                .orElse(null);
    }

    @Override
    public T update(Long id, T entity) {
        Optional<T> optionalEntityFromDB = getRepository().findById(id);
        return optionalEntityFromDB
                .map(e -> saveAndReturnSavedEntity(entity, e))
                .orElse(null);
    }

    private T saveAndReturnSavedEntity(T entity, T entityFromDB) {
        entity.setId(entityFromDB.getId());
        return getRepository().save(entity);
    }

    @Override
    public T save(T entity) {
        return getRepository().save(entity);
    }

    @Override
    public void delete(T entity) {
        Long id = entity.getId();
        if (id == null) {
            return;
        }

        validateExistingEntityById(id);

        getRepository().delete(entity);
    }

    @Override
    public void delete(Long id) {
        validateExistingEntityById(id);

        getRepository().deleteById(id);
    }

    private void validateExistingEntityById(Long id) {
        getRepository()
                .findById(id)
                .orElse(null);
    }
}