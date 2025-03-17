package com.brilhatte.app.enums;

import io.netty.util.internal.MathUtil;

import java.math.BigDecimal;
import java.util.*;

public enum TamanhoHotfixEnum {
    VINTE_QUATRO(Map.of(
            BigDecimal.valueOf(0), BigDecimal.valueOf(0),
            BigDecimal.valueOf(0.01), BigDecimal.valueOf(0.3),
            BigDecimal.valueOf(0.5), BigDecimal.valueOf(0.55),
            BigDecimal.valueOf(0.75), BigDecimal.valueOf(0.8),
            BigDecimal.valueOf(1), BigDecimal.valueOf(1.05)
    ), new BigDecimal(24)),

    TRINTA_DOIS(Map.of(
            BigDecimal.valueOf(0), BigDecimal.valueOf(0),
            BigDecimal.valueOf(0.01), BigDecimal.valueOf(0.35),
            BigDecimal.valueOf(0.50), BigDecimal.valueOf(0.7),
            BigDecimal.valueOf(0.75), BigDecimal.valueOf(1.05),
            BigDecimal.valueOf(1), BigDecimal.valueOf(1.35)
    ), new BigDecimal(32));

    private final NavigableMap<BigDecimal, BigDecimal> valoresPorComprimento;
    private BigDecimal largura;

    TamanhoHotfixEnum(Map<BigDecimal, BigDecimal> valoresPorComprimento, BigDecimal largura) {
        this.valoresPorComprimento = new TreeMap<>(valoresPorComprimento);
        this.largura = largura;
    }

    public BigDecimal calcularValorMetro(BigDecimal comprimento) {
        if (comprimento.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }

        Map.Entry<BigDecimal, BigDecimal> entry = valoresPorComprimento.floorEntry(comprimento);

        return entry.getValue();
    }
    public BigDecimal getLargura() {
        return largura;
    }
}
