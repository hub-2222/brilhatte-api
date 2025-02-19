package com.brilhatte.app.repositories.calculo;

import com.brilhatte.app.common.CustomQuerydslPredicateExecutor;
import com.brilhatte.app.models.calculo.Calculo;
import com.brilhatte.app.models.calculo.Hotfix;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotfixRepository extends JpaRepository<Hotfix, Long>, CustomQuerydslPredicateExecutor<Hotfix> {
}
