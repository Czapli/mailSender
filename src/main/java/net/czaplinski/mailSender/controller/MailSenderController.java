package net.czaplinski.mailSender.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import net.czaplinski.mailSender.domain.model.MailContent;
import net.czaplinski.mailSender.service.MailSenderService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/send")
@RequiredArgsConstructor
public class MailSenderController {
    private final MailSenderService service;

    @Operation(description = "This method sent the given text to all email addresses in the database")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> send(@RequestBody MailContent mailContent) {
        List<String> addressList = service.prepareMails(mailContent);
        return ResponseEntity.ok("Sent " + addressList.size() + " emails");
    }
}
