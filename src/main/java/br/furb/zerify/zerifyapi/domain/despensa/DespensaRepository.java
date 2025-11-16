package br.furb.zerify.zerifyapi.domain.despensa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DespensaRepository extends JpaRepository<DespensaEntity, UUID> {
}
