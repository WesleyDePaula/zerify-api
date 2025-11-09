package br.furb.zerify.zerifyapi.schedule;

import br.furb.zerify.zerifyapi.domain.SendEmailInput;
import br.furb.zerify.zerifyapi.domain.alimento.AlimentoEntity;
import br.furb.zerify.zerifyapi.services.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.time.LocalDate;

@Component
@Slf4j
public class NotificationSchedule {

    @Autowired
    private NotificationService notificationService;

    /**
     * Executa todo dia as 07:00 da manhã
     */
//    @Scheduled(cron = "0 0 7 ? * *")
    @Scheduled(cron = "10 * * * * *")
    public void publishNotification() {
        log.info("### Iniciando envio de email alerta");

        /**
         * TODO: Implementar regra para buscar usuários e alimentos a serem notificados
         * Deve buscar todos os alimentos que estão com a data próxima da validade (verificar na análise qual o periodo min de dias)
         * agrupar os alimentos por usuário
         * para cada usuário, enviar email contendo as informações dos alimentos que estão vencendo
         */

        AlimentoEntity alimentoTeste = new AlimentoEntity();
        alimentoTeste.setNome("Leite Integral");
        alimentoTeste.setDataValidade(LocalDate.of(2025, 9, 11));

        var subject = "⚠ Alerta: Alimento(s) perto da data de valdiade";
        var mensagem = new StringBuilder();
        mensagem.append("Os seguintes alimentos estão próximos da data de validade, consuma-os antes do prazo:\n");

        mensagem.append(MessageFormat.format("- {0}: data de validade até {1}", alimentoTeste.getNome(), alimentoTeste.getDataValidade()));

        var email = new SendEmailInput(subject, mensagem.toString(), "wesleydepaulapsn@gmail.com");
        notificationService.publishNotification(email);

    }

}
