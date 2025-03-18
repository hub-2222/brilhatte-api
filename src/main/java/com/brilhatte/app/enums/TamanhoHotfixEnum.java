package com.brilhatte.app.enums;


import java.math.BigDecimal;

public enum TamanhoHotfixEnum {
    VINTE_QUATRO(new BigDecimal("1.1"), new BigDecimal(24)),

    TRINTA_DOIS(new BigDecimal("1.4"), new BigDecimal(32));

    private BigDecimal valorMetro;
    private BigDecimal largura;

    TamanhoHotfixEnum(BigDecimal valorMetro, BigDecimal largura) {
        this.valorMetro = valorMetro;
        this.largura = largura;
    }

    public BigDecimal getValorMetro() {

        return valorMetro;
    }

    public BigDecimal getLargura() {
        return largura;
    }
}
