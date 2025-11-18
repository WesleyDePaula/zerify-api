package br.furb.zerify.zerifyapi.domain.alimento;

import java.time.LocalDate;
import java.util.UUID;

import br.furb.zerify.zerifyapi.domain.despensa.DespensaEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name="alimento")
@Data
@FieldDefaults(level=AccessLevel.PRIVATE)
public class AlimentoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	UUID id;
	
	@Column(name="nome")
	String nome;
	
	@Column(name="categoria")
	String categoria;
	
	@Column(name="quantidade")
	Long quantidade;
	
	@Column(name="unidade")
    @Enumerated(EnumType.STRING)
	EnumTipoUnidade unidade;
	
	@Column(name="data_validade")
	LocalDate dataValidade;
	
	@Column(name="situacao")
    @Enumerated(EnumType.STRING)
	EnumSituacaoAlimento situacao;
	
	@ManyToOne
	@JoinColumn(name="despensa_id")
    DespensaEntity despensa;
}
