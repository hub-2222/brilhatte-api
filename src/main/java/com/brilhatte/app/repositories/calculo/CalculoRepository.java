package com.brilhatte.app.repositories.calculo;

import com.brilhatte.app.common.CustomQuerydslPredicateExecutor;
import com.brilhatte.app.models.calculo.Calculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalculoRepository extends JpaRepository<Calculo, Long>, CustomQuerydslPredicateExecutor<Calculo> {
    Calculo findByRoupaId(Long roupaId);
}
