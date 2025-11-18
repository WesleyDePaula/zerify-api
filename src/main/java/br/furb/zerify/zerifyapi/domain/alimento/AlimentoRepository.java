package br.furb.zerify.zerifyapi.domain.alimento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AlimentoRepository extends JpaRepository<AlimentoEntity, UUID> {

    Page<AlimentoEntity> findByDespensaId(UUID despensaId, Pageable page);

}
