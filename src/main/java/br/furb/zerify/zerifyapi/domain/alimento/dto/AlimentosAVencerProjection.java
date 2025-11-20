package br.furb.zerify.zerifyapi.domain.alimento.dto;

import br.furb.zerify.zerifyapi.domain.alimento.AlimentoEntity;
import lombok.Getter;

import java.util.List;

@Getter
public class AlimentosAVencerProjection {

    private final String usuarioEmail;
    private final List<AlimentoEntity> alimentos;

    public AlimentosAVencerProjection(String usuarioEmail, List<AlimentoEntity> alimentos) {
        this.usuarioEmail = usuarioEmail;
        this.alimentos = alimentos;
    }

}
