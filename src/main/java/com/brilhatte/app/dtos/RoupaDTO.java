package com.brilhatte.app.dtos;

import com.brilhatte.app.models.Roupa;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;

public class RoupaDTO {

    private Long id;
    private String nome;
    private BigDecimal comprimentoFrente;
    private BigDecimal comprimentoCostas;
    private BigDecimal larguraFrente;
    private BigDecimal larguraCostas;
    private List<PedraVinculadaDTO> pedrasVinculadas;

    public RoupaDTO() {
    }

    public RoupaDTO(Long id, String nome, BigDecimal comprimentoFrente, BigDecimal comprimentoCostas, BigDecimal larguraFrente, BigDecimal larguraCostas, List<PedraVinculadaDTO> pedrasVinculadas) {
        this.id = id;
        this.nome = nome;
        this.comprimentoFrente = comprimentoFrente;
        this.comprimentoCostas = comprimentoCostas;
        this.larguraFrente = larguraFrente;
        this.larguraCostas = larguraCostas;
        this.pedrasVinculadas = pedrasVinculadas;
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

    public BigDecimal getComprimentoFrente() {
        return comprimentoFrente;
    }

    public void setComprimentoFrente(BigDecimal comprimentoFrente) {
        this.comprimentoFrente = comprimentoFrente;
    }

    public BigDecimal getComprimentoCostas() {
        return comprimentoCostas;
    }

    public void setComprimentoCostas(BigDecimal comprimentoCostas) {
        this.comprimentoCostas = comprimentoCostas;
    }

    public BigDecimal getLarguraFrente() {
        return larguraFrente;
    }

    public void setLarguraFrente(BigDecimal larguraFrente) {
        this.larguraFrente = larguraFrente;
    }

    public BigDecimal getLarguraCostas() {
        return larguraCostas;
    }

    public void setLarguraCostas(BigDecimal larguraCostas) {
        this.larguraCostas = larguraCostas;
    }

    public List<PedraVinculadaDTO> getPedrasVinculadas() {
        return pedrasVinculadas;
    }

    public void setPedrasVinculadas(List<PedraVinculadaDTO> pedrasVinculadas) {
        this.pedrasVinculadas = pedrasVinculadas;
    }

    public static RoupaDTO fromEntity(Roupa roupa) {
        RoupaDTO roupaDTO = new RoupaDTO();
        roupaDTO.setId(roupa.getId());
        roupaDTO.setNome(roupa.getNome());
        roupaDTO.setComprimentoFrente(roupa.getComprimentoFrente());
        roupaDTO.setComprimentoCostas(roupa.getComprimentoCostas());
        roupaDTO.setLarguraFrente(roupa.getLarguraFrente());
        roupaDTO.setLarguraCostas(roupa.getLarguraCostas());
        return roupaDTO;
    }

    public static Roupa toEntity(RoupaDTO roupaDTO) {
        Roupa roupa = new Roupa();
        roupa.setId(roupaDTO.getId());
        roupa.setNome(roupaDTO.getNome());
        roupa.setComprimentoFrente(roupaDTO.getComprimentoFrente());
        roupa.setComprimentoCostas(roupaDTO.getComprimentoCostas());
        roupa.setLarguraFrente(roupaDTO.getLarguraFrente());
        roupa.setLarguraCostas(roupaDTO.getLarguraCostas());
        return roupa;
    }

    public static List<RoupaDTO> fromEntities(List<Roupa> roupas) {
        return roupas.stream().map(RoupaDTO::fromEntity).toList();
    }

    public static Page<RoupaDTO> fromEntity(Page<Roupa> roupas) {
        return roupas.map(RoupaDTO::fromEntity);
    }
}
