package com.myprojects.invoice.services;

import com.myprojects.invoice.domain.Mail;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SimpleMailServiceTest {

    @InjectMocks
    private SimpleMailService simpleMailService;
    @Mock
    private JavaMailSender javaMailSender;
    @Mock
    private MimeMessagePreparator mimeMessagePreparator;

    @Test
    public void shouldSendInformationMail() throws Exception {

        //Given
        Mail mail = Mail.builder()
                .mailTo("test@test.com")
                .subject("Test")
                .message("Test Message")
                .toCc(null)
                .attachmentName(null)
                .invoice(null)
                .build();
//        Mail mail = new Mail("test@test.com", "Test", "Test Message",null);
//        MimeMessage message = javaMailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message);
//        String to = mail.getMailTo();
//        helper.setTo(to);
//        helper.setSubject(mail.getSubject());
//        helper.setText(mail.getMessage());
//        helper.setCc(mail.getToCc());
//        helper.addAttachment(mail.getAttachmentName(), mail.getInvoice());
//        mimeMessagePreparator.prepare(message);
        MimeMessage message = javaMailSender.createMimeMessage();
        message.setRecipients(Message.RecipientType.TO, mail.getMailTo());
        message.setSubject(mail.getSubject());
        message.setText(mail.getMessage());
        mimeMessagePreparator.prepare(message);

        //When
        simpleMailService.sendMail(mail);

        //Then
        verify(javaMailSender, times(1)).send(message);
    }
}