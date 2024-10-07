package com.brilhatte.app.dtos;

import java.math.BigDecimal;
import java.util.List;

public class CalculoDTO {

    private List<HotfixDTO> listHotfix;
    private RoupaDTO roupa;
    private BigDecimal maoObra;
    private BigDecimal porcentagemLucro;

    public List<HotfixDTO> getListHotfix() {
        return listHotfix;
    }

    public void setListHotfix(List<HotfixDTO> listHotfix) {
        this.listHotfix = listHotfix;
    }

    public BigDecimal getMaoObra() {
        return maoObra;
    }

    public void setMaoObra(BigDecimal maoObra) {
        this.maoObra = maoObra;
    }

    public BigDecimal getPorcentagemLucro() {
        return porcentagemLucro;
    }

    public void setPorcentagemLucro(BigDecimal porcentagemLucro) {
        this.porcentagemLucro = porcentagemLucro;
    }

    public RoupaDTO getRoupa() {
        return roupa;
    }

    public void setRoupa(RoupaDTO roupa) {
        this.roupa = roupa;
    }
}
