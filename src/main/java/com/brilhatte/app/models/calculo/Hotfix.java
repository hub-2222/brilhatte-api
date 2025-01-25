package com.brilhatte.app.models.calculo;

import com.brilhatte.app.common.AbstractEntity;
import com.brilhatte.app.enums.TamanhoHotfixEnum;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "hotfix")
@SequenceGenerator(name = AbstractEntity.SEQUENCE_GENERATOR, sequenceName = "seq_hotfix", allocationSize = 1)
public class Hotfix extends AbstractEntity{

    @Column(name = "tamanho", nullable = false)
    @Enumerated(EnumType.STRING)
    private TamanhoHotfixEnum tamanho;

    @Column(name = "largura_utilizada", nullable = false)
    private BigDecimal larguraUtilizada = new BigDecimal(0);

    @Column(name = "comprimento_utilizado", nullable = false)
    private BigDecimal comprimentoUtilizado = new BigDecimal(0);

    @JoinColumn(name = "id_calculo", referencedColumnName = "id")
    @ManyToOne
    private Calculo calculo;

    public Hotfix(TamanhoHotfixEnum tamanho, BigDecimal larguraUtilizada, BigDecimal comprimentoUtilizado, Calculo calculo) {
        this.tamanho = tamanho;
        this.larguraUtilizada = larguraUtilizada;
        this.comprimentoUtilizado = comprimentoUtilizado;
        this.calculo = calculo;
    }

    public Hotfix() {
    }

    public TamanhoHotfixEnum getTamanho() {
        return tamanho;
    }

    public void setTamanho(TamanhoHotfixEnum tamanho) {
        this.tamanho = tamanho;
    }

    public BigDecimal getLarguraUtilizada() {
        return larguraUtilizada;
    }

    public void setLarguraUtilizada(BigDecimal larguraUtilizada) {
        this.larguraUtilizada = larguraUtilizada;
    }

    public BigDecimal getComprimentoUtilizado() {
        return comprimentoUtilizado;
    }

    public void setComprimentoUtilizado(BigDecimal comprimentoUtilizado) {
        this.comprimentoUtilizado = comprimentoUtilizado;
    }

    public Calculo getCalculo() {
        return calculo;
    }

    public void setCalculo(Calculo calculo) {
        this.calculo = calculo;
    }
}
