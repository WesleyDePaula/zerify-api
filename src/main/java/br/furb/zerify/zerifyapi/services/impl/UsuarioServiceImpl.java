package br.furb.zerify.zerifyapi.services.impl;

import br.furb.zerify.zerifyapi.auth.AuthorizationService;
import br.furb.zerify.zerifyapi.auth.TokenService;
import br.furb.zerify.zerifyapi.domain.usuario.dto.AuthenticationInputDTO;
import br.furb.zerify.zerifyapi.domain.usuario.dto.RegisterInputDTO;
import br.furb.zerify.zerifyapi.domain.usuario.dto.UpdateUsuarioInputDTO;
import br.furb.zerify.zerifyapi.domain.usuario.UsuarioEntity;
import br.furb.zerify.zerifyapi.domain.usuario.UsuarioRepository;
import br.furb.zerify.zerifyapi.exceptions.ServiceException;
import br.furb.zerify.zerifyapi.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthorizationService authorizationService;

    @Override
    @Transactional
    public UsuarioEntity cadastrarUsuario(RegisterInputDTO dto) {

        if (usuarioRepository.findByEmail(dto.email()) != null) throw new ServiceException(HttpStatus.BAD_REQUEST, "Já existe um usuário com este email");

        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.senha());

        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setSenha(encryptedPassword);
        usuario.setPlano(dto.tipoPlano());

        return usuarioRepository.save(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public String loginUsuario(AuthenticationInputDTO authenticationDTO) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(authenticationDTO.email(), authenticationDTO.senha());
        var auth = authenticationManager.authenticate(usernamePassword);

        return tokenService.gerarToken((UsuarioEntity) auth.getPrincipal());
    }

    @Override
    public UsuarioEntity getUsuarioAtual() {
        return authorizationService.getCurrentUser();
    }

    @Override
    @Transactional
    public UsuarioEntity updateUsuario(UpdateUsuarioInputDTO dto) {
        var usuario = authorizationService.getCurrentUser();
        usuario.setNome(dto.nome());
        return usuarioRepository.save(usuario);
    }
}
