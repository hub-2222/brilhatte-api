package com.brilhatte.app.controllers;

import com.brilhatte.app.dtos.PedraVinculadaDTO;
import com.brilhatte.app.dtos.RoupaDTO;
import com.brilhatte.app.models.Regra;
import com.brilhatte.app.models.Roupa;
import com.brilhatte.app.services.RegraService;
import com.brilhatte.app.services.RoupaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/roupas")
public class RoupaController {

    @Autowired
    private RoupaService roupaService;

    @Autowired
    private RegraService regraService;

    @GetMapping
    public ResponseEntity<Page<RoupaDTO>> findAll(@RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "10") int size) {
        Page<Roupa> roupas = roupaService.findAllByRoupaId(PageRequest.of(page, size));
        Page<RoupaDTO> roupasDTO = RoupaDTO.fromEntity(roupas);
        roupasDTO.forEach(dto -> {
            List<Regra> regras = regraService.findAllByRoupaId(dto.getId());
            dto.setPedrasVinculadas(PedraVinculadaDTO.fromRegras(regras));
        });

        return ResponseEntity.ok(roupasDTO);
    }
}
