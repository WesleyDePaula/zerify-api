package br.furb.zerify.zerifyapi.services;

import br.furb.zerify.zerifyapi.domain.SendEmailInput;

public interface NotificationService {

    void publishNotification(SendEmailInput emailInput);

}
