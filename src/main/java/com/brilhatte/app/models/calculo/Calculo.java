package com.brilhatte.app.models.calculo;


import com.brilhatte.app.common.AbstractEntity;
import com.brilhatte.app.models.Roupa;
import jakarta.persistence.*;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "calculo")
@SequenceGenerator(name = AbstractEntity.SEQUENCE_GENERATOR, sequenceName = "seq_calculo", allocationSize = 1)
public class Calculo extends AbstractEntity{

    @JoinColumn(name = "id_roupa", referencedColumnName = "id")
    @OneToOne
    private Roupa roupa;

    @Column(name = "mao_obra", nullable = false)
    private BigDecimal maoObra = new BigDecimal(0);

    @Column(name = "porcentagem_lucro", nullable = false)
    private BigDecimal porcentagemLucro = new BigDecimal(0);

    @Column(name = "preco_custo", nullable = false)
    private BigDecimal precoCusto = new BigDecimal(0);

    @OneToMany(mappedBy = "id_calculo")
    @OrderBy("id ASC")
    private List<Hotfix> hotfixes;

    public Calculo(Roupa roupa, BigDecimal maoObra, BigDecimal porcentagemLucro, BigDecimal precoCusto) {
        this.roupa = roupa;
        this.maoObra = maoObra;
        this.porcentagemLucro = porcentagemLucro;
        this.precoCusto = precoCusto;
    }

    public Calculo() {
    }

    public Roupa getRoupa() {
        return roupa;
    }

    public void setRoupa(Roupa roupa) {
        this.roupa = roupa;
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

    public BigDecimal getPrecoCusto() {
        return precoCusto;
    }

    public void setPrecoCusto(BigDecimal precoCusto) {
        this.precoCusto = precoCusto;
    }

    public List<Hotfix> getHotfixes() {
        return hotfixes;
    }

    public void setHotfixes(List<Hotfix> hotfixes) {
        this.hotfixes = hotfixes;
    }

    public void addHotfix(Hotfix hotfix){
        this.hotfixes.add(hotfix);
    }

    public void removeHotfix(Hotfix hotfix){
        this.hotfixes.remove(hotfix);
    }
}
