package br.furb.zerify.zerifyapi.domain.despensa;

import br.furb.zerify.zerifyapi.domain.usuario.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DespensaRepository extends JpaRepository<DespensaEntity, UUID> {

    Optional<DespensaEntity> findByUsuario(UsuarioEntity usuario);

}
