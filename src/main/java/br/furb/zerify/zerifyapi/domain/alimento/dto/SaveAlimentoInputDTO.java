package br.furb.zerify.zerifyapi.domain.alimento.dto;

import br.furb.zerify.zerifyapi.domain.alimento.EnumTipoUnidade;
import br.furb.zerify.zerifyapi.domain.alimento.EnumSituacaoAlimento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record SaveAlimentoInputDTO(
        String id, // Opcional: usado apenas para update

        @NotNull
        @NotBlank
        String despensaId,

        @NotNull
        @NotBlank
        String nome,

        @NotNull
        @NotBlank
        String categoria,

        @NotNull
        Long quantidade,

        @NotNull
        EnumTipoUnidade unidade,

        @NotNull
        LocalDate dataValidade,

        EnumSituacaoAlimento situacao
) {
}
