package net.czaplinski.mailSender.service;

import net.czaplinski.mailSender.domain.model.Mail;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@ExtendWith(MockitoExtension.class)
class MailSenderServiceTest {
    @InjectMocks
    MailSenderService service;
    @Mock
    JavaMailSender javaMailSender;


    @Test
    public void shouldSendEmail() {
        //Given
        Mail mail = new Mail("test@test.pl", "test subject", "test message");
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getAddress());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());

        //When
        service.send(mail);

        //Then
        Mockito.verify(javaMailSender, Mockito.times(1)).send(mailMessage);
    }
}