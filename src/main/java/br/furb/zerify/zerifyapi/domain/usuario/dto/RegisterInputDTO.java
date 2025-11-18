package br.furb.zerify.zerifyapi.domain.usuario.dto;

import br.furb.zerify.zerifyapi.domain.usuario.TipoPlano;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterInputDTO(
        @NotNull @NotBlank
        String nome,

        @NotNull @NotBlank @Email
        String email,

        @NotNull @NotBlank
        String senha,

        @NotNull
        TipoPlano tipoPlano) {
}
