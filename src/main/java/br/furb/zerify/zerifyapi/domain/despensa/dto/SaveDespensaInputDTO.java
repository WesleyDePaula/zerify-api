package br.furb.zerify.zerifyapi.domain.despensa.dto;

import jakarta.validation.constraints.NotNull;

public record SaveDespensaInputDTO(
        @NotNull String nome
) {}
