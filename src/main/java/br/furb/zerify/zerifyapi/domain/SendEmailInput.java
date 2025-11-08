package br.furb.zerify.zerifyapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendEmailInput {
    String subject;
    String message;
    String userEmail;
}
