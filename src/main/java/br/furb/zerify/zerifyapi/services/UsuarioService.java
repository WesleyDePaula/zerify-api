package br.furb.zerify.zerifyapi.services;

import br.furb.zerify.zerifyapi.domain.usuario.AuthenticationDTO;
import br.furb.zerify.zerifyapi.domain.usuario.RegisterDTO;
import br.furb.zerify.zerifyapi.domain.usuario.UsuarioEntity;

public interface UsuarioService {

    UsuarioEntity cadastrarUsuario(RegisterDTO dto);
    String loginUsuario(AuthenticationDTO authenticationDTO);

}
