package com.myprojects.invoice.mappers;

import com.myprojects.invoice.domain.Mail;
import com.myprojects.invoice.domain.dtos.MailDto;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
public class MailMapper {

    public Mail mapToMail(final @NotNull MailDto mailDto) {
        return new Mail(
                mailDto.getMailTo(),
                mailDto.getSubject(),
                mailDto.getMessage(),
                mailDto.getToCc(),
                mailDto.getAttachmentName(),
                mailDto.getInvoice()
        );
    }

    public MailDto mapToMailDto(final @NotNull Mail mail) {
        return new MailDto(
                mail.getMailTo(),
                mail.getSubject(),
                mail.getMessage(),
                mail.getToCc(),
                mail.getAttachmentName(),
                mail.getInvoice()
        );
    }
}
