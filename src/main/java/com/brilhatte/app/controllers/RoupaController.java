package com.brilhatte.app.controllers;

import com.brilhatte.app.dtos.PedraVinculadaDTO;
import com.brilhatte.app.dtos.RoupaDTO;
import com.brilhatte.app.models.Regra;
import com.brilhatte.app.models.Roupa;
import com.brilhatte.app.services.RegraService;
import com.brilhatte.app.services.RoupaService;
import com.brilhatte.app.services.calculo.CalculoService;
import com.brilhatte.app.validators.RoupaValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/roupas")
public class RoupaController {

    @Autowired
    private RoupaService roupaService;

    @Autowired
    private RegraService regraService;

    @Autowired
    private RoupaValidator roupaValidator;

    @Autowired
    private CalculoService calculoService;

    @GetMapping
    public ResponseEntity<Page<RoupaDTO>> findAll(@RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "10") int size,
                                                  @RequestParam(defaultValue = "") String nome) {
        Page<Roupa> roupas = roupaService.findAllByNomeWithImage(PageRequest.of(page, size), nome);
        Page<RoupaDTO> roupasDTO = RoupaDTO.fromEntity(roupas);
        roupasDTO.forEach(dto -> {
            List<Regra> regras = regraService.findAllByRoupaId(dto.getId());
            dto.setPedrasVinculadas(PedraVinculadaDTO.fromRegras(regras));
        });

        return ResponseEntity.ok(roupasDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoupaDTO> findById(@PathVariable Long id) {
        Roupa roupa = roupaService.findById(id);
        List<Regra> regras = regraService.findAllByRoupaId(id);
        RoupaDTO roupaDTO = RoupaDTO.fromEntity(roupa);
        roupaDTO.setPedrasVinculadas(PedraVinculadaDTO.fromRegras(regras));
        return ResponseEntity.ok(roupaDTO);
    }

    @PostMapping
    public ResponseEntity<RoupaDTO> save(@RequestBody RoupaDTO roupaDTO) {
        roupaValidator.validateFields(roupaDTO);

        Roupa roupa = roupaService.save(RoupaDTO.toEntity(roupaDTO));
        regraService.create(roupa, roupaDTO.getPedrasVinculadas());
        return ResponseEntity.ok(RoupaDTO.fromEntity(roupa));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoupaDTO> update(@PathVariable Long id, @RequestBody RoupaDTO roupaDTO) {
        roupaValidator.validateFields(roupaDTO);

        Roupa roupa = roupaService.update(id, RoupaDTO.toEntity(roupaDTO));
        regraService.update(roupa, roupaDTO.getPedrasVinculadas());
        return ResponseEntity.ok(RoupaDTO.fromEntity(roupa));
    }

    @DeleteMapping("/{idRoupa}")
    public ResponseEntity<Void> delete(@PathVariable Long idRoupa) {
        calculoService.deleteByRoupaId(idRoupa);
        regraService.deleteByRoupaId(idRoupa);
        roupaService.delete(idRoupa);
        return ResponseEntity.noContent().build();
    }
}
