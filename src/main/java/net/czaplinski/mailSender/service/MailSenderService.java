package net.czaplinski.mailSender.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.czaplinski.mailSender.domain.model.Mail;
import net.czaplinski.mailSender.domain.model.MailContent;
import org.springframework.mail.MailException;
import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailSenderService {
    private final MailAddressesService addressesService;
    private final JavaMailSender javaMailSender;

    public void send(Mail mail) {
        try {
            javaMailSender.send(createMailMessage(mail));
            log.info("Email has been sent to: " + mail.getAddress());
        } catch (MailException e) {
            log.error("Failed to process email sending to: " + mail.getAddress() + " - reason: " + e.getMessage());
        }
    }

    private SimpleMailMessage createMailMessage(Mail mail) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getAddress());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());
        return mailMessage;
    }

    public void prepareMails(MailContent content) {

        addressesService.getAllAddresses().forEach(address -> {
                    send(Mail.builder()
                            .address(address.getEmailAddress())
                            .subject(content.getSubject())
                            .message(content.getMessage())
                            .build());
                }
        );
    }
}

