package com.brilhatte.app.enums;

import java.math.BigDecimal;

public enum TamanhoHotfixEnum {
    VINTE_QUATRO(new BigDecimal("0.75"), 24),
    TRINTA_DOIS(new BigDecimal(1), 32);

    private BigDecimal precoMetro;
    private Integer largura;

    TamanhoHotfixEnum(BigDecimal precoMetro, Integer largura) {
        this.precoMetro = precoMetro;
        this.largura = largura;
    }

    public BigDecimal getPrecoMetro() {
        return precoMetro;
    }

    public Integer getLargura() {
        return largura;
    }
}
