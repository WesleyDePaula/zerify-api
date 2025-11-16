package br.furb.zerify.zerifyapi.domain.despensa;

import java.util.List;
import java.util.UUID;

import br.furb.zerify.zerifyapi.domain.alimento.AlimentoEntity;
import br.furb.zerify.zerifyapi.domain.usuario.UsuarioEntity;
import jakarta.persistence.*;
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
	
	@OneToOne //TODO: Apenas um usuário possui uma despensa e a despensa pode ter apenas um usuário?
	@JoinColumn(name="usuario_id")
    UsuarioEntity usuario;
	
}
