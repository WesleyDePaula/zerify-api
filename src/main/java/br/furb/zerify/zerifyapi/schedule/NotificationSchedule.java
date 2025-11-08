package br.furb.zerify.zerifyapi.schedule;

import br.furb.zerify.zerifyapi.domain.SendEmailInput;
import br.furb.zerify.zerifyapi.services.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NotificationSchedule {

    @Autowired
    private NotificationService notificationService;

    /**
     * Executa todo dia as 07:00 da manhã
     */
    @Scheduled(cron = "0 0 7 ? * *")
    public void publishNotification() {
        log.info("### Iniciando envio de email alerta");

        // TODO: Implementar regra para buscar usuários e alimentos a serem notificados

        var email = new SendEmailInput("subject", "message", "userEmail");
        notificationService.publishNotification(email);

    }

}
