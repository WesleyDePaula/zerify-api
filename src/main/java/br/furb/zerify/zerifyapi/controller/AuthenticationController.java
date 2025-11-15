package br.furb.zerify.zerifyapi.controller;

import br.furb.zerify.zerifyapi.domain.usuario.*;
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
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO authenticationDTO) {
        return ResponseEntity.ok(new LoginResponseDTO(usuarioService.loginUsuario(authenticationDTO)));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterDTO registerDTO) {
        usuarioService.cadastrarUsuario(registerDTO);
        var token = usuarioService.loginUsuario(new AuthenticationDTO(registerDTO.email(), registerDTO.senha()));
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

}
