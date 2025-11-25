package br.furb.zerify.zerifyapi.services.impl;

import br.furb.zerify.zerifyapi.domain.alimento.AlimentoEntity;
import br.furb.zerify.zerifyapi.domain.alimento.AlimentoRepository;
import br.furb.zerify.zerifyapi.domain.alimento.EnumSituacaoAlimento;
import br.furb.zerify.zerifyapi.domain.alimento.dto.SaveAlimentoInputDTO;
import br.furb.zerify.zerifyapi.domain.despensa.DespensaRepository;
import br.furb.zerify.zerifyapi.exceptions.ServiceException;
import br.furb.zerify.zerifyapi.services.AlimentoService;
import br.furb.zerify.zerifyapi.services.UsuarioService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class AlimentoServiceImpl implements AlimentoService {

    @Autowired
    private AlimentoRepository alimentoRepository;

    @Autowired
    private DespensaRepository despensaRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Override
    @Transactional
    public AlimentoEntity save(@Valid SaveAlimentoInputDTO alimentoDTO) {
        var despensa = despensaRepository.findById(UUID.fromString(alimentoDTO.despensaId()))
                .orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, "Despensa não encontrada"));

        var usuarioAtual = usuarioService.getUsuarioAtual();

        if (!despensa.getUsuario().equals(usuarioAtual)) {
            throw new ServiceException(HttpStatus.UNAUTHORIZED, "Não é possível alterar os alimentos de uma despensa de outro usuário");
        }

        var alimento = new AlimentoEntity();
        alimento.setNome(alimentoDTO.nome());
        alimento.setCategoria(alimentoDTO.categoria());
        alimento.setQuantidade(alimentoDTO.quantidade());
        alimento.setDataValidade(alimentoDTO.dataValidade());
        alimento.setUnidade(alimentoDTO.unidade());

        alimento.setDespensa(despensa);
        alimento.setSituacao(alimentoDTO.situacao() != null ? alimentoDTO.situacao() : EnumSituacaoAlimento.PARA_COMPRAR);

        return alimentoRepository.save(alimento);
    }

    @Override
    @Transactional
    public AlimentoEntity update(@Valid SaveAlimentoInputDTO alimentoDTO) {
        var alimentoId = UUID.fromString(alimentoDTO.id());
        var alimento = alimentoRepository.findById(alimentoId)
                .orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, "Alimento não encontrado"));

        var usuarioAtual = usuarioService.getUsuarioAtual();

        if (!alimento.getDespensa().getUsuario().equals(usuarioAtual)) {
            throw new ServiceException(HttpStatus.UNAUTHORIZED, "Não é possível alterar os alimentos de uma despensa de outro usuário");
        }

        alimento.setNome(alimentoDTO.nome());
        alimento.setCategoria(alimentoDTO.categoria());
        alimento.setQuantidade(alimentoDTO.quantidade());
        alimento.setDataValidade(alimentoDTO.dataValidade());
        alimento.setUnidade(alimentoDTO.unidade());

        if (alimentoDTO.situacao() != null) {
            alimento.setSituacao(alimentoDTO.situacao());
        }

        return alimentoRepository.save(alimento);
    }

    @Override
    @Transactional
    public void delete(UUID alimentoId) {
        var alimento = alimentoRepository.findById(alimentoId)
                .orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, "Alimento não encontrado"));

        var usuarioAtual = usuarioService.getUsuarioAtual();

        if (!alimento.getDespensa().getUsuario().equals(usuarioAtual)) {
            throw new ServiceException(HttpStatus.UNAUTHORIZED, "Não é possível excluir alimentos de uma despensa de outro usuário");
        }

        alimentoRepository.delete(alimento);
    }

    @Override
    public Page<AlimentoEntity> listByDespensa(@NotNull UUID despensaId, Pageable page) {
        return alimentoRepository.findByDespensaId(despensaId, page);
    }

}
