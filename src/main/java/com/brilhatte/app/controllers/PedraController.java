package com.brilhatte.app.controllers;

import com.brilhatte.app.dtos.PedraDTO;
import com.brilhatte.app.models.Pedra;
import com.brilhatte.app.services.PedraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedras")
public class PedraController {

    @Autowired
    private PedraService service;

    @GetMapping
    public ResponseEntity findAll(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size,
                                  @RequestParam(defaultValue = "") String nome) {
        Page<Pedra> pedras = service.findAll(PageRequest.of(page, size, Sort.by(Sort.Order.asc("nome"))), nome);
        Page<PedraDTO> pedrasDTO = PedraDTO.fromEntity(pedras);
        return ResponseEntity.ok(pedrasDTO);
    }
}
