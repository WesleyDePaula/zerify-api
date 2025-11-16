package br.furb.zerify.zerifyapi.controller;

import br.furb.zerify.zerifyapi.domain.usuario.dto.AuthenticationInputDTO;
import br.furb.zerify.zerifyapi.domain.usuario.dto.LoginResponseDTO;
import br.furb.zerify.zerifyapi.domain.usuario.dto.RegisterInputDTO;
import br.furb.zerify.zerifyapi.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationInputDTO authenticationDTO) {
        return ResponseEntity.ok(new LoginResponseDTO(usuarioService.loginUsuario(authenticationDTO)));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterInputDTO registerDTO) {
        usuarioService.cadastrarUsuario(registerDTO);
        var token = usuarioService.loginUsuario(new AuthenticationInputDTO(registerDTO.email(), registerDTO.senha()));
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

}
