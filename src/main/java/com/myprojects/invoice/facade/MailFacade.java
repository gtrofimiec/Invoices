package com.myprojects.invoice.facade;

import com.myprojects.invoice.domain.Mail;
import com.myprojects.invoice.domain.dtos.MailDto;
import com.myprojects.invoice.mappers.MailMapper;
import com.myprojects.invoice.services.SimpleMailService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class MailFacade {

    private final MailMapper mailMapper;
    private final SimpleMailService simpleMailService;

    public boolean sendMail(MailDto mailDto) {
        Mail mail = mailMapper.mapToMail(mailDto);
        return simpleMailService.sendMail(mail);
    }
}
