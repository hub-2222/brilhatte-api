package com.brilhatte.app.controllers;

import com.brilhatte.app.dtos.CalculoDTO;
import com.brilhatte.app.dtos.HotfixDTO;
import com.brilhatte.app.models.Regra;
import com.brilhatte.app.services.RegraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("/calculo")
public class CalculoController {

    private static final BigDecimal PORCENTAGEM_GRADUACAO = new BigDecimal("1.05");
    private static final BigDecimal ONE_HUNDRED = new BigDecimal(100);

    @Autowired
    private RegraService regraService;

    @PostMapping
    public ResponseEntity<BigDecimal> calcularPrecoCusto(@RequestBody CalculoDTO calculoDTO) {
        BigDecimal valorTotalHotfix = calculoDTO.getListHotfix().stream()
                .map(CalculoController::calcularValorHotfix)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        List<Regra> regras = regraService.findAllByRoupaId(calculoDTO.getRoupa().getId());
        BigDecimal valorTotalPedras = calcularValorPedras(regras);
        BigDecimal porcentagemLucro = calculoDTO.getPorcentagemLucro().divide(ONE_HUNDRED, 2, RoundingMode.HALF_UP).add(BigDecimal.ONE);

        BigDecimal precoCusto = (valorTotalHotfix.add(calculoDTO.getMaoObra()).add(valorTotalPedras)).multiply(porcentagemLucro);
        return ResponseEntity.ok(precoCusto);
    }

    private static BigDecimal calcularValorPedras(List<Regra> regras) {
        return regras.stream().map(regra -> {
            BigDecimal valorPedra = regra.getPedra().getValorUnitario();
            Integer quantidadePedra = regra.getQuantidade();

            return valorPedra.multiply(BigDecimal.valueOf(quantidadePedra)).multiply(PORCENTAGEM_GRADUACAO);
        }).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private static BigDecimal calcularValorHotfix( HotfixDTO hotfix) {
        BigDecimal valorMetro = hotfix.getTamanho().getPrecoMetro();
        Integer larguraHotfix = hotfix.getTamanho().getLargura();
        BigDecimal comprimentoMetro = hotfix.getComprimentoUtilizado().divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);
        BigDecimal proporcaoLargura = hotfix.getLarguraUtilizada().divide(new BigDecimal(larguraHotfix), 2, RoundingMode.HALF_UP);
        BigDecimal valorRealMetro = valorMetro.multiply(proporcaoLargura);

        return valorRealMetro.multiply(comprimentoMetro);
    }
}
