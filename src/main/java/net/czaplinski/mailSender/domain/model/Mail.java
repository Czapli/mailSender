package net.czaplinski.mailSender.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
@AllArgsConstructor
public class Mail {
    private String address;
    private String subject;
    private String message;

}
