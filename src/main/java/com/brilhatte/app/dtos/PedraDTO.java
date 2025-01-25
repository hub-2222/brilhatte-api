package com.brilhatte.app.dtos;

import com.brilhatte.app.models.Pedra;
import jakarta.persistence.Column;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class PedraDTO {

    private Long id;
    private String nome;
    private String tamanho;
    private BigDecimal valorUnitario;

    public PedraDTO() {
    }

    public PedraDTO(Long id, String nome, String tamanho, BigDecimal valorUnitario) {
        this.id = id;
        this.nome = nome;
        this.tamanho = tamanho;
        this.valorUnitario = valorUnitario;
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

    public static PedraDTO fromEntity(Pedra pedra) {
        return new PedraDTO(pedra.getId(), pedra.getNome(), pedra.getTamanho(), pedra.getValorUnitario());
    }

    public static Pedra toEntity(PedraDTO pedraDTO) {
        return new Pedra(pedraDTO.getNome(), pedraDTO.getTamanho(), pedraDTO.getValorUnitario());
    }

    public static Page<PedraDTO> fromEntity(Page<Pedra> pedras) {
        return pedras.map(PedraDTO::fromEntity);
    }
}
