package br.furb.zerify.zerifyapi.domain.despensa;

import java.util.List;
import java.util.UUID;

import br.furb.zerify.zerifyapi.domain.alimento.AlimentoEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@Table(name="despensa")
@FieldDefaults(level=AccessLevel.PRIVATE)
public class DespensaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	UUID id;
	
	@Column(name="nome")
	String nome;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "despensa")
	List<AlimentoEntity> alimentos;
	
	// TODO: Criar entidade usuário e verificar relacionamento
//	@ManyToOne
//	@JoinColumn(name="usuario_id")
//	UsuarioEntity usuario;
	
}
