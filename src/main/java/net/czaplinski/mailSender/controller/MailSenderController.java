package net.czaplinski.mailSender.controller;

import lombok.RequiredArgsConstructor;
import net.czaplinski.mailSender.domain.model.MailContent;
import net.czaplinski.mailSender.service.MailSenderService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/send")
@RequiredArgsConstructor
public class MailSenderController {
    private final MailSenderService service;
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> send(@RequestBody MailContent mailContent){
        service.prepareMails(mailContent);
        return ResponseEntity.ok().build();
    }
}
