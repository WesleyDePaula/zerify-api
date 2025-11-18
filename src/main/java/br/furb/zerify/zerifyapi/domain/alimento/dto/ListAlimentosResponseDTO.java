package br.furb.zerify.zerifyapi.domain.alimento.dto;

import br.furb.zerify.zerifyapi.domain.alimento.AlimentoEntity;
import br.furb.zerify.zerifyapi.domain.alimento.EnumSituacaoAlimento;
import br.furb.zerify.zerifyapi.domain.alimento.EnumTipoUnidade;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public record ListAlimentosResponseDTO(
        String id,
        String nome,
        String categoria,
        Long quantidade,
        EnumTipoUnidade unidade,
        LocalDate dataValidade,
        EnumSituacaoAlimento situacao
) {

    public static ListAlimentosResponseDTO convertToRecord(AlimentoEntity alimento) {
        return new ListAlimentosResponseDTO(
                String.valueOf(alimento.getId()),
                alimento.getNome(),
                alimento.getCategoria(),
                alimento.getQuantidade(),
                alimento.getUnidade(),
                alimento.getDataValidade(),
                alimento.getSituacao()
        );
    }

}
