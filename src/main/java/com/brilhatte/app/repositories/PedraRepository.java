package com.brilhatte.app.repositories;

import com.brilhatte.app.common.CustomQuerydslPredicateExecutor;
import com.brilhatte.app.models.Pedra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedraRepository extends JpaRepository<Pedra, Long>, CustomQuerydslPredicateExecutor<Pedra> {
}
