package com.myprojects.invoice.facade;

import com.myprojects.invoice.domain.Invoices;
import com.myprojects.invoice.domain.dtos.InvoicesDto;
import com.myprojects.invoice.exceptions.CustomerNotFoundException;
import com.myprojects.invoice.exceptions.InvoiceAlreadyExistsException;
import com.myprojects.invoice.exceptions.InvoicesNotFoundException;
import com.myprojects.invoice.exceptions.ProductNotFoundException;
import com.myprojects.invoice.mappers.InvoicesMapper;
import com.myprojects.invoice.services.InvoicesService;
import com.myprojects.invoice.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class InvoicesFacade {

    private final InvoicesService invoicesService;
    private final InvoicesMapper invoicesMapper;
    private final UserService userService;

    public List<InvoicesDto> getInvoices() {
        List<InvoicesDto> list = invoicesMapper.mapToInvoicesDtoList(invoicesService.getAll());
        return invoicesMapper.mapToInvoicesDtoList(invoicesService.getAll());
    }

    public InvoicesDto getInvoice(Long id) throws InvoicesNotFoundException {
        return invoicesMapper.mapToInvoiceDto(invoicesService.getOne(id));
    }

    public InvoicesDto saveInvoice(InvoicesDto invoiceDto)
            throws InvoiceAlreadyExistsException {
        Invoices newInvoice = invoicesMapper.mapToInvoice(invoiceDto);
//        newInvoice.setUser(userService.getOne(userId));
        invoicesService.save(newInvoice);
        return invoicesMapper.mapToInvoiceDto(newInvoice);
    }

    public InvoicesDto addCustomerToInvoice(Long invoiceId, Long customerId)
            throws InvoicesNotFoundException, CustomerNotFoundException {
        Invoices invoice = invoicesService.getOne(invoiceId);
        Invoices updatedInvoice = invoicesService.addCustomerToInvoice(invoice, customerId);
        return invoicesMapper.mapToInvoiceDto(updatedInvoice);
    }

    public InvoicesDto addProductToInvoice(Long invoiceId, Long productId)
            throws InvoicesNotFoundException, ProductNotFoundException {
        Invoices invoice = invoicesService.getOne(invoiceId);
        Invoices updatedInvoice = invoicesService.addProductToInvoice(invoice, productId);
        return invoicesMapper.mapToInvoiceDto(updatedInvoice);
    }

    public InvoicesDto updateInvoiceHeader(Long id, InvoicesDto invoiceDto)
            throws InvoicesNotFoundException {
        Invoices updatedInvoice = invoicesMapper.mapToInvoice(invoiceDto);
        updatedInvoice.setId(id);
        invoicesService.update(updatedInvoice);
        return invoicesMapper.mapToInvoiceDto(updatedInvoice);
    }

    public void deleteInvoice(Long invoiceId) throws InvoicesNotFoundException {
        invoicesService.deleteInvoice(invoiceId);
    }

    public InvoicesDto deleteCustomerFromInvoice(Long invoiceId, Long customerId)
            throws InvoicesNotFoundException, CustomerNotFoundException {
        Invoices invoice = invoicesService.getOne(invoiceId);
        Invoices updatedInvoice = invoicesService.deleteCustomerFromInvoice(invoice, customerId);
        return invoicesMapper.mapToInvoiceDto(updatedInvoice);
    }

    public InvoicesDto deleteProductFromInvoice(Long invoiceId, Long productId)
            throws InvoicesNotFoundException, ProductNotFoundException {
        Invoices invoice = invoicesService.getOne(invoiceId);
        Invoices updatedInvoice = invoicesService.deleteProductFromInvoice(invoice, productId);
        return invoicesMapper.mapToInvoiceDto(updatedInvoice);
    }
}