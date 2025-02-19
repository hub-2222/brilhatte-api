package com.brilhatte.app.dtos.calculo;

import com.brilhatte.app.dtos.RoupaDTO;
import com.brilhatte.app.models.calculo.Calculo;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class CalculoDTO {

    private Long id;
    private List<HotfixDTO> listHotfix;
    private RoupaDTO roupa;
    private BigDecimal maoObra;
    private BigDecimal porcentagemLucro;
    private BigDecimal precoCusto;

    public CalculoDTO(Long id, List<HotfixDTO> listHotfix, RoupaDTO roupa, BigDecimal maoObra, BigDecimal porcentagemLucro, BigDecimal precoCusto) {
        this.id = id;
        this.listHotfix = listHotfix;
        this.roupa = roupa;
        this.maoObra = maoObra;
        this.porcentagemLucro = porcentagemLucro;
        this.precoCusto = precoCusto;
    }

    public CalculoDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public BigDecimal getPrecoCusto() {
        return precoCusto;
    }

    public void setPrecoCusto(BigDecimal precoCusto) {
        this.precoCusto = precoCusto;
    }

    public static CalculoDTO fromEntity(Calculo calculo, List<HotfixDTO> hotfixes){
        return new CalculoDTO(calculo.getId(), hotfixes, RoupaDTO.fromEntity(calculo.getRoupa()), calculo.getMaoObra(), calculo.getPorcentagemLucro(), calculo.getPrecoCusto());
    }

    public static Calculo toEntity(CalculoDTO calculoDTO){
        return new Calculo(RoupaDTO.toEntity(calculoDTO.getRoupa()), calculoDTO.getMaoObra(), calculoDTO.getPorcentagemLucro(), calculoDTO.getPrecoCusto());
    }
}
