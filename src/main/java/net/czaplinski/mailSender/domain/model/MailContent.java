package net.czaplinski.mailSender.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MailContent {
    private String subject;
    private String message;
}
