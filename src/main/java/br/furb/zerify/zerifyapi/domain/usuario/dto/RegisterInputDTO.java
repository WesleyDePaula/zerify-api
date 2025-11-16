package br.furb.zerify.zerifyapi.domain.usuario.dto;

import br.furb.zerify.zerifyapi.domain.usuario.TipoPlano;

public record RegisterInputDTO(String nome, String email, String senha, TipoPlano tipoPlano) {
}
