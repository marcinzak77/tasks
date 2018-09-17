package com.crud.tasks.service;

import com.crud.tasks.config.MailConfig;
import com.crud.tasks.domain.Mail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SimpleEmailServiceTest {

    @InjectMocks
    private SimpleEmailService simpleEmailService;

    @Mock
    private MailCreatorService mailCreatorService;

    @Mock
    private JavaMailSender javaMailSender;

    @Mock
    private MailConfig mailConfig;

    @Test
    public void shouldSendEmail() {
        //Given
        Mail mail = new Mail("test@test.com", null, "test", "message");

        when(mailConfig.getSenderMail()).thenReturn("testFrom@testFrom.com");
        when(mailCreatorService.buildTrelloCardEmail(anyString())).thenReturn("message");

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("testFrom@testFrom.com");
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());

        //When
        simpleEmailService.send(mail);

        //Then
        verify(javaMailSender, times(1)).send(mailMessage);
    }

}