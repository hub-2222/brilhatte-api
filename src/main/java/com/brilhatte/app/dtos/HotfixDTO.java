package com.brilhatte.app.dtos;

import com.brilhatte.app.enums.TamanhoHotfixEnum;

import java.math.BigDecimal;

public class HotfixDTO {

    private TamanhoHotfixEnum tamanho;
    private BigDecimal larguraUtilizada;
    private BigDecimal comprimentoUtilizado;

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
}
