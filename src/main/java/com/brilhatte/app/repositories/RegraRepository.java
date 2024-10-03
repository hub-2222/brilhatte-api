package com.brilhatte.app.repositories;

import com.brilhatte.app.common.CustomQuerydslPredicateExecutor;
import com.brilhatte.app.models.Regra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegraRepository extends JpaRepository<Regra, Long>, CustomQuerydslPredicateExecutor<Regra> {
}
