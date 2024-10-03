package com.brilhatte.app.models;

import com.brilhatte.app.common.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "pedras")
@SequenceGenerator(name = AbstractEntity.SEQUENCE_GENERATOR, sequenceName = "seq_pedras", allocationSize = 1)
public class Pedra extends AbstractEntity {

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "tamanho", nullable = false)
    private String tamanho;

    @Column(name ="valor_unitario", nullable = false)
    private BigDecimal valorUnitario;

    public Pedra() {
    }

    public Pedra(String nome, String tamanho, BigDecimal valorUnitario) {
        this.nome = nome;
        this.tamanho = tamanho;
        this.valorUnitario = valorUnitario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }
}
