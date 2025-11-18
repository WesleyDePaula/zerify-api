package br.furb.zerify.zerifyapi.auth;

import br.furb.zerify.zerifyapi.domain.usuario.UsuarioEntity;
import br.furb.zerify.zerifyapi.domain.usuario.UsuarioRepository;
import br.furb.zerify.zerifyapi.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return usuarioRepository.findByEmail(email);
    }

    public UsuarioEntity getCurrentUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            throw new ServiceException(HttpStatus.NOT_FOUND, "Usuário autenticado não encontrado");
        }

        return (UsuarioEntity) authentication.getPrincipal();
    }
}
