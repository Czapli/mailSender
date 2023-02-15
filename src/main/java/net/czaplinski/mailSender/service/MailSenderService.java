package net.czaplinski.mailSender.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.czaplinski.mailSender.domain.model.Mail;
import net.czaplinski.mailSender.domain.model.MailContent;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailSenderService {
    private final MailAddressesService addressesService;
    private final JavaMailSender javaMailSender;

    public void send(Mail mail) {
        try {
            javaMailSender.send(createMailMessage(mail));
            log.trace("Email has been sent to: " + mail.getAddress());
        } catch (MailException e) {
            log.warn("Failed to process email sending to: " + mail.getAddress() + " - reason: " + e.getMessage());
        }
    }

    private SimpleMailMessage createMailMessage(Mail mail) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getAddress());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());
        return mailMessage;
    }

    public List<String> prepareMails(MailContent content) {
        ArrayList<String> addressList = new ArrayList<>();
        addressesService.getAllAddresses().forEach(address -> {
            addressList.add(address.getEmailAddress());
            send(Mail.builder()
                    .address(address.getEmailAddress())
                    .subject(content.getSubject())
                    .message(content.getMessage())
                    .build());
        });
        return addressList;
    }
}

