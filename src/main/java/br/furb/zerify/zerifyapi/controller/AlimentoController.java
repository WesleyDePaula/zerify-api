package br.furb.zerify.zerifyapi.controller;

import br.furb.zerify.zerifyapi.domain.alimento.dto.ListAlimentosResponseDTO;
import br.furb.zerify.zerifyapi.domain.alimento.dto.SaveAlimentoInputDTO;
import br.furb.zerify.zerifyapi.domain.alimento.dto.SaveAlimentoResponseDTO;
import br.furb.zerify.zerifyapi.services.AlimentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/alimento")
public class AlimentoController {

    @Autowired
    private AlimentoService alimentoService;

    @PostMapping
    public ResponseEntity<SaveAlimentoResponseDTO> save(@RequestBody @Valid SaveAlimentoInputDTO alimentoDTO) {
        var alimento = alimentoService.save(alimentoDTO);
        return ResponseEntity.ok(new SaveAlimentoResponseDTO(alimento != null
                ? String.valueOf(alimento.getId())
                : null));
    }

    @PutMapping
    public ResponseEntity<ListAlimentosResponseDTO> update(@RequestBody @Valid SaveAlimentoInputDTO alimentoDTO) {
        var alimento = alimentoService.update(alimentoDTO);
        return ResponseEntity.ok(ListAlimentosResponseDTO.convertToRecord(alimento));
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam("id") String alimentoId) {
        alimentoService.delete(UUID.fromString(alimentoId));
        return ResponseEntity.noContent().build();
    }

    @GetMapping()
    public ResponseEntity<Page<ListAlimentosResponseDTO>> list(
            @Param("despensaId") String despensaId,
            @PageableDefault(size = 10, sort = {"nome"}) Pageable page
    ) {
        var alimentos = alimentoService.listByDespensa(UUID.fromString(despensaId), page);
        return ResponseEntity.ok(alimentos.map(ListAlimentosResponseDTO::convertToRecord));
    }

}
