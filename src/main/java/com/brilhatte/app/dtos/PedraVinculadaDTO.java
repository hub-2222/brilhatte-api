package com.brilhatte.app.dtos;

import com.brilhatte.app.models.Pedra;
import com.brilhatte.app.models.Regra;

import java.math.BigDecimal;
import java.util.List;

public class PedraVinculadaDTO {

    private Long id;
    private String nome;
    private String tamanho;
    private BigDecimal valorUnitario;
    private Integer quantidade;
    private Long idRegra;

    public PedraVinculadaDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Long getIdRegra() {
        return idRegra;
    }

    public void setIdRegra(Long idRegra) {
        this.idRegra = idRegra;
    }

    public static PedraVinculadaDTO fromRegra(Regra regra) {
        PedraVinculadaDTO pedraVinculadaDTO = new PedraVinculadaDTO();
        Pedra pedra = regra.getPedra();
        pedraVinculadaDTO.setId(pedra.getId());
        pedraVinculadaDTO.setNome(pedra.getNome());
        pedraVinculadaDTO.setTamanho(pedra.getTamanho());
        pedraVinculadaDTO.setValorUnitario(pedra.getValorUnitario());
        pedraVinculadaDTO.setQuantidade(regra.getQuantidade());
        pedraVinculadaDTO.setIdRegra(regra.getId());
        return pedraVinculadaDTO;
    }

    public static List<PedraVinculadaDTO> fromRegras(List<Regra> regras) {
        return regras.stream().map(PedraVinculadaDTO::fromRegra).toList();
    }
}
