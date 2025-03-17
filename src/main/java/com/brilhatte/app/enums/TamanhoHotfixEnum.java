package com.brilhatte.app.enums;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public enum TamanhoHotfixEnum {
    VINTE_QUATRO(Map.of(
            BigDecimal.valueOf(0.25), BigDecimal.valueOf(0.3),
            BigDecimal.valueOf(0.5), BigDecimal.valueOf(0.55),
            BigDecimal.valueOf(0.75), BigDecimal.valueOf(0.8),
            BigDecimal.valueOf(1.0), BigDecimal.valueOf(1.05)
    ), 24),

    TRINTA_DOIS(Map.of(
            BigDecimal.valueOf(0.25), BigDecimal.valueOf(0.35),
            BigDecimal.valueOf(0.5), BigDecimal.valueOf(0.7),
            BigDecimal.valueOf(0.75), BigDecimal.valueOf(1.05),
            BigDecimal.valueOf(1.0), BigDecimal.valueOf(1.35)
    ), 32);

    private final NavigableMap<BigDecimal, BigDecimal> valoresPorComprimento;
    private Integer largura;

    TamanhoHotfixEnum(Map<BigDecimal, BigDecimal> valoresPorComprimento, Integer largura) {
        this.valoresPorComprimento = new TreeMap<>(valoresPorComprimento);
        this.largura = largura;
    }

    public BigDecimal calcularValorMetro(BigDecimal comprimento) {
        Map.Entry<BigDecimal, BigDecimal> entry = valoresPorComprimento.ceilingEntry(comprimento);
        return (entry != null) ? entry.getValue() : BigDecimal.ZERO;
    }

    public Integer getLargura() {
        return largura;
    }
}
