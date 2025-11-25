package br.furb.zerify.zerifyapi.domain.usuario.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateUsuarioInputDTO(
        @NotBlank
        String nome
) {
}
