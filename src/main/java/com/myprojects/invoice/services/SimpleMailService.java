package com.myprojects.invoice.services;

import com.myprojects.invoice.domain.Mail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
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

    @Autowired
    private final JavaMailSender javaMailSender;
    @Autowired
    private final MailCreatorService mailCreatorService;

    public boolean sendMail(final Mail mail) {
        log.info("Starting email preparation...");
        try {
            javaMailSender.send(createMessage(mail));
            log.info("Email has been sent.");
            return true;
        } catch(MailException e) {
            log.error("Failed to process email sending: " + e.getMessage(), e);
            return false;
        }
    }

    @Contract(pure = true)
    private @NotNull MimeMessagePreparator createMessage(final Mail mail) {
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
}