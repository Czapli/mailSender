package net.czaplinski.mailSender.domain.model;

import lombok.Getter;

@Getter
public class MailContent {
    private String subject;
    private String message;
}
