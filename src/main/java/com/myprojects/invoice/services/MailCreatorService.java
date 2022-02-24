package com.myprojects.invoice.services;

import com.myprojects.invoice.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.LocalDate;

@Service
public class MailCreatorService {

    @Autowired
    private AdminConfig adminConfig;
    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildEmail(String message) {
        Context context = new Context();
        context.setVariable("preview", "Faktura VAT");
        context.setVariable("welcome", "Witam");
        context.setVariable("url", "http://localhost:8082");
        context.setVariable("button", "Visit website");
        context.setVariable("show_button", false);
        context.setVariable("message", message);
        context.setVariable("goodbye", "Z poważaniem");
        context.setVariable("admin_config", adminConfig);
        context.setVariable("company_details", "GeoSoft Corp. " + LocalDate.now().getYear());
        return templateEngine.process("invoiceMail/created-mail", context);
    }
}