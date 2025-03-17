package com.brilhatte.app.models;

import com.brilhatte.app.common.AbstractEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "regras")
@SequenceGenerator(name = AbstractEntity.SEQUENCE_GENERATOR, sequenceName = "seq_regras", allocationSize = 1)
public class Regra extends AbstractEntity {

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    @ManyToOne
    @JoinColumn(name = "id_pedra", nullable = false, referencedColumnName = "id")
    @OrderBy(value = "id asc")
    private Pedra pedra;

    @ManyToOne
    @JoinColumn(name = "id_roupa", nullable = false, referencedColumnName = "id")
    private Roupa roupa;

    public Regra() {
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Pedra getPedra() {
        return pedra;
    }

    public void setPedra(Pedra pedra) {
        this.pedra = pedra;
    }

    public Roupa getRoupa() {
        return roupa;
    }

    public void setRoupa(Roupa roupa) {
        this.roupa = roupa;
    }


}
