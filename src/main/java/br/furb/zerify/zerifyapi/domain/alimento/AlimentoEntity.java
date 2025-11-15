package br.furb.zerify.zerifyapi.domain.alimento;

import java.time.LocalDate;
import java.util.UUID;

import br.furb.zerify.zerifyapi.domain.despensa.DespensaEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
	Integer quantidade;
	
	@Column(name="unidade")
	EnumTipoUnidade unidade;
	
	@Column(name="data_validade")
	LocalDate dataValidade;
	
	@Column(name="situacao")
	EnumSituacaoAlimento situacao;
	
	// TODO: Verificar relacionamento com entidade Despensa (Teremos uma tabela intermediária? ou será ManyToOne?)
	@ManyToOne
	@JoinColumn(name="despensa_id")
	DespensaEntity despensa;
}
