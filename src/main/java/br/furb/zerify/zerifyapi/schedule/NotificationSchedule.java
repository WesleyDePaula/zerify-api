package br.furb.zerify.zerifyapi.schedule;

import br.furb.zerify.zerifyapi.domain.SendEmailInput;
import br.furb.zerify.zerifyapi.domain.alimento.AlimentoRepository;
import br.furb.zerify.zerifyapi.domain.alimento.dto.AlimentosAVencerProjection;
import br.furb.zerify.zerifyapi.services.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
@Slf4j
public class NotificationSchedule {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private AlimentoRepository alimentoRepository;

    /**
     * Executa todo dia as 07:00 da manhã
     */
//    @Scheduled(cron = "0 0 7 ? * *")
    @Scheduled(cron = "10 * * * * *")
    public void publishNotification() {
        log.info("### Iniciando envio de email alerta");

        List<AlimentosAVencerProjection> alimentos = alimentoRepository.findAlimentosVencidosOuProximos();
        alimentos.stream().map(this::buildSendEmailInput).forEach(notificationService::publishNotification);

    }

    private SendEmailInput buildSendEmailInput(AlimentosAVencerProjection alimentosAVencerProjection) {
        var mensagem = new StringBuilder();
        int total = alimentosAVencerProjection.getAlimentos().size();
        var subject = MessageFormat.format("⚠ Alerta: {0} alimento(s) perto/fora da data de validade", total);

        mensagem.append(MessageFormat.format("Olá, encontramos {0} alimento(s) que estão vencidos ou próximos da data de validade:\n\n", total));

        alimentosAVencerProjection.getAlimentos().forEach(alimento -> {
            LocalDate hoje = LocalDate.now();
            LocalDate validade = alimento.getDataValidade();
            long dias = ChronoUnit.DAYS.between(hoje, validade);
            String prazo;
            if (validade == null) {
                prazo = "data de validade não informada";
            } else if (dias < 0) {
                prazo = MessageFormat.format("vencido há {0} dia(s)", Math.abs(dias));
            } else if (dias == 0) {
                prazo = "vence hoje";
            } else {
                prazo = MessageFormat.format("vence em {0} dia(s)", dias);
            }

            mensagem.append(MessageFormat.format("- {0} (data de validade: {1}) — {2}\n", alimento.getNome(), validade, prazo));
        });

        mensagem.append("\nO que você pode fazer:\n");
        mensagem.append("- Consumir os alimentos listados o quanto antes.\n");
        mensagem.append("- Se preferir, atualize a quantidade ou remova o alimento da sua despensa no site.\n\n");

        mensagem.append("Atenciosamente,\n");
        mensagem.append("Equipe Zerify\n");
        mensagem.append("\nObservação: Este é um e-mail automático com alertas de validade. Por favor não responda\n");

        return new SendEmailInput(subject, mensagem.toString(), alimentosAVencerProjection.getUsuarioEmail());

    }

}
