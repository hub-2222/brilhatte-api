package com.brilhatte.app.models;

import com.brilhatte.app.common.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "roupas")
@SequenceGenerator(name = AbstractEntity.SEQUENCE_GENERATOR, sequenceName = "seq_roupas", allocationSize = 1)
public class Roupa extends AbstractEntity{

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "comprimento_frente", nullable = false)
    private BigDecimal comprimentoFrente;

    @Column(name = "comprimento_costas", nullable = false)
    private BigDecimal comprimentoCostas;

    @Column(name = "largura_frente", nullable = false)
    private BigDecimal larguraFrente;

    @Column(name = "largura_costas", nullable = false)
    private BigDecimal larguraCostas;

    public Roupa() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getComprimentoFrente() {
        return comprimentoFrente;
    }

    public void setComprimentoFrente(BigDecimal comprimentoFrente) {
        this.comprimentoFrente = comprimentoFrente;
    }

    public BigDecimal getComprimentoCostas() {
        return comprimentoCostas;
    }

    public void setComprimentoCostas(BigDecimal comprimentoCostas) {
        this.comprimentoCostas = comprimentoCostas;
    }

    public BigDecimal getLarguraFrente() {
        return larguraFrente;
    }

    public void setLarguraFrente(BigDecimal larguraFrente) {
        this.larguraFrente = larguraFrente;
    }

    public BigDecimal getLarguraCostas() {
        return larguraCostas;
    }

    public void setLarguraCostas(BigDecimal larguraCostas) {
        this.larguraCostas = larguraCostas;
    }
}
