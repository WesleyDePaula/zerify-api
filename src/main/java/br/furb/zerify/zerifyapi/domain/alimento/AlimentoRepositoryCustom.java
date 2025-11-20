package br.furb.zerify.zerifyapi.domain.alimento;

import br.furb.zerify.zerifyapi.domain.alimento.dto.AlimentosAVencerProjection;

import java.util.List;

public interface AlimentoRepositoryCustom {

    List<AlimentosAVencerProjection> findAlimentosVencidosOuProximos();

}
