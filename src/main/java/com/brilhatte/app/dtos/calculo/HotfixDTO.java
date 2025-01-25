package com.brilhatte.app.dtos.calculo;

import com.brilhatte.app.enums.TamanhoHotfixEnum;
import com.brilhatte.app.models.calculo.Calculo;
import com.brilhatte.app.models.calculo.Hotfix;

import java.math.BigDecimal;

public class HotfixDTO {

    private Long id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HotfixDTO(Long id,  TamanhoHotfixEnum tamanho, BigDecimal larguraUtilizada, BigDecimal comprimentoUtilizado) {
        this.id = id;
        this.tamanho = tamanho;
        this.larguraUtilizada = larguraUtilizada;
        this.comprimentoUtilizado = comprimentoUtilizado;
    }

    public HotfixDTO() {
    }

    public static HotfixDTO fromEntity(Hotfix hotfix) {
        return new HotfixDTO(hotfix.getId(), hotfix.getTamanho(), hotfix.getLarguraUtilizada(), hotfix.getComprimentoUtilizado());
    }

    public static Hotfix toEntity(HotfixDTO hotfixDTO, Calculo calculo) {
        return new Hotfix(hotfixDTO.getTamanho(), hotfixDTO.getLarguraUtilizada(), hotfixDTO.getComprimentoUtilizado(), calculo);
    }
}
