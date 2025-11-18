package br.furb.zerify.zerifyapi.services.impl;

import br.furb.zerify.zerifyapi.auth.AuthorizationService;
import br.furb.zerify.zerifyapi.domain.despensa.DespensaEntity;
import br.furb.zerify.zerifyapi.domain.despensa.DespensaRepository;
import br.furb.zerify.zerifyapi.domain.despensa.dto.SaveDespensaInputDTO;
import br.furb.zerify.zerifyapi.services.DespensaService;
import br.furb.zerify.zerifyapi.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DespensaServiceImpl implements DespensaService {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private DespensaRepository despensaRepository;

    @Override
    public DespensaEntity saveDespensa(SaveDespensaInputDTO despensaDto) {
        var despensa = new DespensaEntity();
        despensa.setNome(despensaDto.nome());
        despensa.setUsuario(usuarioService.getUsuarioAtual());

        return despensaRepository.save(despensa);
    }

    @Override
    public DespensaEntity retrieveDespensa() {
        return despensaRepository.findByUsuario(usuarioService.getUsuarioAtual())
                .orElse(null);
    }

}
