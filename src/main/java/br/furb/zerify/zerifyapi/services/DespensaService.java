package br.furb.zerify.zerifyapi.services;

import br.furb.zerify.zerifyapi.domain.despensa.DespensaEntity;
import br.furb.zerify.zerifyapi.domain.despensa.dto.SaveDespensaInputDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface DespensaService {

    DespensaEntity saveDespensa(@Valid SaveDespensaInputDTO despensaDto);

}
