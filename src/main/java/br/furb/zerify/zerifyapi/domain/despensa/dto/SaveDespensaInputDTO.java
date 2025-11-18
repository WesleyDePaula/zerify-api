package br.furb.zerify.zerifyapi.domain.despensa.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SaveDespensaInputDTO(
        @NotNull @NotBlank String nome
) {}
