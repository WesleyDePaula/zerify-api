package br.furb.zerify.zerifyapi.domain;

public record SendEmailInput(String subject, String message, String userEmail) {
}
