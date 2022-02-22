package com.myprojects.invoice.services;

import com.myprojects.invoice.domain.Mail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SimpleMailService {

    private final JavaMailSender javaMailSender;
    private final MailCreatorService mailCreatorService;

    public boolean sendInvoice(final Mail mail) {
        log.info("Starting email preparation...");
        try {
            javaMailSender.send(createInvoiceMessage(mail));
            log.info("Email has been sent.");
            return true;
        } catch(MailException e) {
            log.error("Failed to process email sending: " + e.getMessage(), e);
            return false;
        }
    }

    public void sendInformation(final Mail mail) {
        log.info("Starting email preparation...");

        try {
//            SimpleMailMessage mailMessage = createMailMessage(mail);
//            javaMailSender.send(mailMessage);
//            log.info("Email has been sent.");
            javaMailSender.send(createInformationMailMessage(mail));
            log.info("Email has been sent.");
        } catch(MailException e) {
            log.error("Failed to process email sending: " + e.getMessage(), e);
        }
    }


    private MimeMessagePreparator createInvoiceMessage(final Mail mail) {
        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
            messageHelper.setTo(mail.getMailTo());
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setText(mailCreatorService.buildInvoiceEmail(mail.getMessage()), true);
            Optional.ofNullable(mail.getToCc()).ifPresent(cc -> {
                try {
                    mimeMessage.addRecipients(Message.RecipientType.CC, cc);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            });
            Optional.ofNullable(mail.getAttachmentName()).ifPresent(att -> {
                try {
                    messageHelper.addAttachment(att, mail.getInvoice());
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            });
        };
    }

    private @NotNull SimpleMailMessage createInformationMailMessage(final @NotNull Mail mail) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mailCreatorService.buildInformationEmail(mail.getMessage()));
        return mailMessage;
    }

//    private @NotNull SimpleMailMessage createMailMessage(final @NotNull Mail mail) {
//        SimpleMailMessage mailMessage = new SimpleMailMessage();
//
//        mailMessage.setTo(mail.getMailTo());
//        mailMessage.setSubject(mail.getSubject());
//        mailMessage.setText(mail.getMessage());
//        Optional.ofNullable(mail.getToCc()).ifPresent(cc -> mailMessage.setCc(mail.getToCc()));
//        return mailMessage;
//    }
}