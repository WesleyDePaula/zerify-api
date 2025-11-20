package br.furb.zerify.zerifyapi.domain.alimento;

import br.furb.zerify.zerifyapi.domain.alimento.dto.AlimentosAVencerProjection;
import br.furb.zerify.zerifyapi.domain.despensa.QDespensaEntity;
import br.furb.zerify.zerifyapi.domain.usuario.QUsuarioEntity;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class AlimentoRepositoryCustomImpl implements AlimentoRepositoryCustom {

    private static final QAlimentoEntity QALIMENTO = QAlimentoEntity.alimentoEntity;
    private static final QDespensaEntity QDESPENSA = QDespensaEntity.despensaEntity;
    private static final QUsuarioEntity QUSUARIO = QUsuarioEntity.usuarioEntity;

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional(readOnly = true)
    public List<AlimentosAVencerProjection> findAlimentosVencidosOuProximos() {
        JPAQuery<Tuple> query = new JPAQuery<>(em);

        LocalDate limite = LocalDate.now().plusDays(3);

        List<Tuple> rows = query
                .select(QUSUARIO.email, QALIMENTO)
                .from(QALIMENTO)
                .join(QALIMENTO.despensa, QDESPENSA)
                .join(QDESPENSA.usuario, QUSUARIO)
                .where(QALIMENTO.situacao.eq(EnumSituacaoAlimento.COMPRADO)
                        .and(QALIMENTO.dataValidade.loe(limite)))
                .fetch();

        if (rows == null || rows.isEmpty()) {
            return List.of();
        }

        Map<String, List<AlimentoEntity>> grouped = rows.stream()
                .collect(Collectors.groupingBy(
                        t -> t.get(QUSUARIO.email),
                        Collectors.mapping(t -> t.get(QALIMENTO), Collectors.toList())
                ));

        return grouped.entrySet()
                .stream()
                .map(e -> new AlimentosAVencerProjection(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
    }

}
