package com.brilhatte.app.repositories;

import com.brilhatte.app.common.CustomQuerydslPredicateExecutor;
import com.brilhatte.app.models.Roupa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoupaRepository extends JpaRepository<Roupa, Long>, CustomQuerydslPredicateExecutor<Roupa> {
}
