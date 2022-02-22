package com.myprojects.invoice.services;

import com.myprojects.invoice.config.AdminConfig;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class MailCreatorService {

    @Autowired
    private final AdminConfig adminConfig;
    @Autowired
    @Qualifier("templateEngine")
    private final TemplateEngine templateEngine;

    public String buildInvoiceEmail(String message) {
        Context context = new Context();
        context.setVariable("preview", "Faktura VAT");
        context.setVariable("welcome", "Witam");
        context.setVariable("message", message);
        context.setVariable("goodbye", "Z poważaniem\n" + adminConfig.getAdminName());
        context.setVariable("company_details", adminConfig.getCompanyName() + " "
                + LocalDate.now().getYear());
        return templateEngine.process("invoiceMail/created-invoice-mail", context);
    }

    public String buildInformationEmail(String message) {
        Context context = new Context();
        context.setVariable("preview", "Informacja o bieżącej sprzedaży");
        context.setVariable("welcome", "Witam");
        context.setVariable("message", message);
        context.setVariable("goodbye", "Z poważaniem\n" + adminConfig.getAdminName());
        context.setVariable("company_details", adminConfig.getCompanyName() + " "
                + LocalDate.now().getYear());
        return templateEngine.process("invoiceMail/created-invoice-mail", context);
    }
}
