package com.brilhatte.app.controllers;

import com.brilhatte.app.dtos.calculo.CalculoDTO;
import com.brilhatte.app.dtos.calculo.HotfixDTO;
import com.brilhatte.app.enums.TamanhoHotfixEnum;
import com.brilhatte.app.models.Regra;
import com.brilhatte.app.models.calculo.Calculo;
import com.brilhatte.app.models.calculo.Hotfix;
import com.brilhatte.app.repositories.calculo.CalculoRepository;
import com.brilhatte.app.services.RegraService;
import com.brilhatte.app.services.calculo.CalculoService;
import com.brilhatte.app.services.calculo.HotfixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.NumberUtils;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/calculo")
public class CalculoController {

    private static final BigDecimal PORCENTAGEM_GRADUACAO = new BigDecimal("1.05");
    private static final BigDecimal ONE_HUNDRED = new BigDecimal(100);
    private static final BigDecimal MARGEM_ERRO_LARGURA = new BigDecimal(4);



    @Autowired
    private CalculoService calculoService;

    @Autowired
    private HotfixService hotfixService;

    @Autowired
    private RegraService regraService;

    @GetMapping("/{idRoupa}")
    public ResponseEntity<CalculoDTO> findByRoupaId(@PathVariable Long idRoupa) {
        Calculo calculo = calculoService.findByRoupaId(idRoupa);
        if (Objects.isNull(calculo)) {
            return ResponseEntity.ok().build();
        }

        List<Hotfix> hotfixes = hotfixService.findAllByCalculoId(calculo.getId());
        return ResponseEntity.ok(CalculoDTO.fromEntity(calculo, HotfixDTO.fromEntity(hotfixes)));
    }

    @PostMapping
    public ResponseEntity<BigDecimal> calcularPrecoCusto(@RequestBody CalculoDTO calculoDTO) {
        BigDecimal valorTotalHotfix = calculoDTO.getListHotfix().stream()
                .map(CalculoController::calcularValorHotfix)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        List<Regra> regras = regraService.findAllByRoupaId(calculoDTO.getRoupa().getId());
        BigDecimal valorTotalPedras = calcularValorPedras(regras);
        BigDecimal porcentagemLucro = calculoDTO.getPorcentagemLucro().divide(ONE_HUNDRED, 2, RoundingMode.HALF_UP).add(BigDecimal.ONE);
        BigDecimal precoCusto = (valorTotalHotfix.add(calculoDTO.getMaoObra()).add(valorTotalPedras)).multiply(porcentagemLucro).setScale(5, RoundingMode.HALF_UP);


        calculoDTO.setPrecoCusto(precoCusto);
        Calculo calculo = CalculoDTO.toEntity(calculoDTO);
        calculo = calculoService.save(calculo);
        List<Hotfix> hotfixes = HotfixDTO.toEntity(calculoDTO.getListHotfix(), calculo);
        hotfixService.update(hotfixes, calculo);
        return ResponseEntity.ok(precoCusto);
    }

    private static BigDecimal calcularValorPedras(List<Regra> regras) {
        return regras.stream().map(regra -> {
            BigDecimal valorPedra = regra.getPedra().getValorUnitario();
            Integer quantidadePedra = regra.getQuantidade();

            return valorPedra.multiply(BigDecimal.valueOf(quantidadePedra)).multiply(PORCENTAGEM_GRADUACAO);
        }).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private static BigDecimal calcularValorHotfix(HotfixDTO hotfix) {
        BigDecimal comprimentoMetro = hotfix.getComprimentoUtilizado().divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);
        BigDecimal valorMetro = hotfix.getTamanho().calcularValorMetro(comprimentoMetro);
        BigDecimal larguraHotfix = hotfix.getTamanho().getLargura();
        BigDecimal larguraCalculo = hotfix.getLarguraUtilizada().add(MARGEM_ERRO_LARGURA);
        if (larguraCalculo.compareTo(larguraHotfix) > 0) {
            larguraCalculo = larguraHotfix;
        }

        BigDecimal proporcaoLargura = larguraCalculo.divide(larguraHotfix, 2, RoundingMode.HALF_UP);
        BigDecimal valorRealMetro = valorMetro.multiply(proporcaoLargura);

        return valorRealMetro.multiply(comprimentoMetro);
    }

}
