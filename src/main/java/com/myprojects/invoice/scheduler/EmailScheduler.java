package com.myprojects.invoice.scheduler;

import com.myprojects.invoice.config.AdminConfig;
import com.myprojects.invoice.domain.Mail;
import com.myprojects.invoice.repositories.InvoicesRepository;
import com.myprojects.invoice.services.SimpleEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailScheduler {

    private static final String SUBJECT = "Invoices: Once a day email";

    private final SimpleEmailService simpleEmailService;
    private final InvoicesRepository invoicesRepository;
    private final AdminConfig adminConfig;

    @Scheduled(cron ="0 0 10 * * *")
    public void sendInformationEmail() {
        long size = invoicesRepository.count();
        simpleEmailService.send(new Mail(
                adminConfig.getAdminMail(),
                SUBJECT,
                "Currently in database you got: " + size + " invoices",
                null
        ));
    }
}