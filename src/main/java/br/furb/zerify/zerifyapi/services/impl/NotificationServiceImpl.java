package br.furb.zerify.zerifyapi.services.impl;

import br.furb.zerify.zerifyapi.domain.SendEmailInput;
import br.furb.zerify.zerifyapi.services.NotificationService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Value("${rabbit.notification_queue}")
    public String notificationQueue;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void publishNotification(SendEmailInput emailInput) {
        rabbitTemplate.convertAndSend(notificationQueue, emailInput);
    }
}
