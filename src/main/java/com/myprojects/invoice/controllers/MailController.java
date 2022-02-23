package com.myprojects.invoice.controllers;

import com.myprojects.invoice.domain.dtos.MailDto;
import com.myprojects.invoice.facade.MailFacade;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@AllArgsConstructor
@RestController
@RequestMapping("v1/invoices")
public class MailController {

    private final MailFacade mailFacade;

    @RequestMapping(method = RequestMethod.POST, value = "/mail",
            consumes = APPLICATION_JSON_VALUE)
    public boolean sendMailWithInvoice(@RequestBody MailDto mailDto) {
        return mailFacade.sendMail(mailDto);
    }
}