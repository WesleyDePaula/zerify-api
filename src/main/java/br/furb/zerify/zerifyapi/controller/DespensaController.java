package br.furb.zerify.zerifyapi.controller;

import br.furb.zerify.zerifyapi.domain.despensa.dto.SaveDespensaInputDTO;
import br.furb.zerify.zerifyapi.domain.despensa.dto.SaveDespensaResponseDTO;
import br.furb.zerify.zerifyapi.services.DespensaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/despensa")
public class DespensaController {

    @Autowired
    private DespensaService despensaService;

    @PostMapping
    public ResponseEntity<SaveDespensaResponseDTO> save(@RequestBody @Valid SaveDespensaInputDTO despensaDTO) {
        var despensaId = despensaService.saveDespensa(despensaDTO);
        return ResponseEntity.ok(new SaveDespensaResponseDTO(despensaId.getId().toString()));
    }

}
