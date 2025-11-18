package br.furb.zerify.zerifyapi.services;

import br.furb.zerify.zerifyapi.domain.alimento.AlimentoEntity;
import br.furb.zerify.zerifyapi.domain.alimento.dto.SaveAlimentoInputDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface AlimentoService {

    AlimentoEntity save(@Valid SaveAlimentoInputDTO alimento);

    Page<AlimentoEntity> listByDespensa(UUID despensaId, Pageable page);
}
