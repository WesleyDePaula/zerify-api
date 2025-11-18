package br.furb.zerify.zerifyapi.domain.usuario;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name="usuario")
@FieldDefaults(level=AccessLevel.PRIVATE)
public class UsuarioEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @Column(name="nome")
    String nome;

    @Column(name="email")
    String email;

    @Column(name="senha")
    String senha;

    @Column(name = "plano")
    @Enumerated(EnumType.STRING)
    TipoPlano plano;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }
}
