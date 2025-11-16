package br.furb.zerify.zerifyapi.services;

import br.furb.zerify.zerifyapi.domain.usuario.dto.AuthenticationInputDTO;
import br.furb.zerify.zerifyapi.domain.usuario.dto.RegisterInputDTO;
import br.furb.zerify.zerifyapi.domain.usuario.UsuarioEntity;

public interface UsuarioService {

    UsuarioEntity cadastrarUsuario(RegisterInputDTO dto);
    String loginUsuario(AuthenticationInputDTO authenticationDTO);

}
