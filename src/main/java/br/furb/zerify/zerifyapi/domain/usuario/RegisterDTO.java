package br.furb.zerify.zerifyapi.domain.usuario;

public record RegisterDTO(String nome, String email, String senha, TipoPlano tipoPlano) {
}
