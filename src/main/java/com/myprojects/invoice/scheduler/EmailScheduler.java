package com.myprojects.invoice.scheduler;

import com.myprojects.invoice.config.AdminConfig;
import com.myprojects.invoice.domain.Invoices;
import com.myprojects.invoice.domain.Mail;
import com.myprojects.invoice.repositories.InvoicesRepository;
import com.myprojects.invoice.services.SimpleMailService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class EmailScheduler {

    private static final String SUBJECT = "Invoices: Once a day email";

    private final SimpleMailService simpleMailService;
    private final InvoicesRepository invoicesRepository;
    private final AdminConfig adminConfig;

    @Scheduled(cron ="0 0 15 * * *")
//    @Scheduled(fixedDelay = 10000)
    public void salesValueUpToNow() {
        BigDecimal size = invoicesRepository.findAll().stream()
                .filter(i -> i.getDate().isBefore(LocalDate.now().plusDays(1)))
                .map(Invoices::getGrossSum)
                .reduce(BigDecimal.valueOf(0), BigDecimal::add);
        simpleMailService.sendMail(new Mail(
                adminConfig.getAdminMail(),
                SUBJECT,
                "Sprzedaż do dnia " + LocalDate.now()+ ": " + size + " zł",
                null,
                null,
                null
        ));
    }
//
//    public void sendInvoice(String mailTo, File invoice) {
//        simpleMailService.sendMail(new Mail(
//                mailTo,
//                "Faktura VAT",
//                "Witam\nW załączniku faktura.",
//                null,
//                invoice.getName(),
//                invoice
//        ));
//    }
}