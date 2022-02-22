package com.myprojects.invoice.services;

import com.myprojects.invoice.domain.Mail;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.io.File;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SimpleMailServiceTest {

    @InjectMocks
    private SimpleMailService simpleMailService;

    @Mock
    private JavaMailSender javaMailSender;

    @Test
    public void shouldSendInformationMail() {

        //Given
        Mail mail = new Mail("test@test.com", "Test", "Test Message", null);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());

        //When
        simpleMailService.sendInformation(mail);

        //Then
        verify(javaMailSender, times(1)).send(mailMessage);
    }
}