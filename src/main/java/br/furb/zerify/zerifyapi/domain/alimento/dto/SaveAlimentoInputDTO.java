package br.furb.zerify.zerifyapi.domain.alimento.dto;

import br.furb.zerify.zerifyapi.domain.alimento.EnumTipoUnidade;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record SaveAlimentoInputDTO(
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
        LocalDate dataValidade) {
}
