package com.myprojects.invoice.controllers;

import com.myprojects.invoice.domain.dtos.InvoicesDto;
import com.myprojects.invoice.exceptions.CustomerNotFoundException;
import com.myprojects.invoice.exceptions.InvoiceAlreadyExistsException;
import com.myprojects.invoice.exceptions.InvoicesNotFoundException;
import com.myprojects.invoice.exceptions.ProductNotFoundException;
import com.myprojects.invoice.facade.InvoicesFacade;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@AllArgsConstructor
@RestController
@RequestMapping("v1/invoices")
public class InvoicesController {

    private final InvoicesFacade invoicesFacade;

    @RequestMapping(method = RequestMethod.GET, value = "/invoices")
    public List<InvoicesDto> getInvoices() {
        return invoicesFacade.getInvoices();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/invoices/{id}")
    public InvoicesDto getInvoices(@PathVariable("id") Long invoiceId) throws InvoicesNotFoundException {
        return invoicesFacade.getInvoices(invoiceId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/invoices/{userId}", consumes = APPLICATION_JSON_VALUE)
    public InvoicesDto saveInvoice(@PathVariable("userId") Long userId, @RequestBody InvoicesDto invoiceDto)
            throws InvoiceAlreadyExistsException {
        return invoicesFacade.saveInvoice(userId, invoiceDto);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/invoices/{invoiceId}/add/{customerId}")
    public InvoicesDto addCustomerToInvoice(@PathVariable("invoiceId") Long invoiceId,
                                            @PathVariable("customerId") Long customerId)
            throws InvoicesNotFoundException, CustomerNotFoundException {
        return invoicesFacade.addCustomerToInvoice(invoiceId, customerId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/invoices/{invoiceId}/add/{productId}")
    public InvoicesDto addProductToInvoice(@PathVariable("invoiceId") Long invoiceId,
                                           @PathVariable("productId") Long productId)
            throws InvoicesNotFoundException, ProductNotFoundException {
        return invoicesFacade.addProductToInvoice(invoiceId, productId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/invoices/{id}")
    public InvoicesDto updateInvoiceHeader(@PathVariable("id") Long invoiceId, @RequestBody InvoicesDto invoiceDto)
            throws InvoicesNotFoundException {
        return invoicesFacade.updateInvoiceHeader(invoiceId, invoiceDto);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/invoices/{id}")
    public void deleteInvoice(@PathVariable("id") Long invoiceId) throws InvoicesNotFoundException {
        invoicesFacade.deleteInvoice(invoiceId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/invoices/{invoiceId}/del/{customerId}")
    public InvoicesDto deleteCustomerFromInvoice(@PathVariable("invoiceId") Long invoiceId,
                                          @PathVariable("customerId") Long customerId)
            throws InvoicesNotFoundException, CustomerNotFoundException {
        return invoicesFacade.deleteCustomerFromInvoice(invoiceId, customerId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/invoices/{invoiceId}/del/{productId}")
    public InvoicesDto deleteProductFromInvoice(@PathVariable("invoiceId") Long invoiceId,
                                                 @PathVariable("productId") Long productId)
            throws InvoicesNotFoundException, ProductNotFoundException {
        return invoicesFacade.deleteProductFromInvoice(invoiceId, productId);
    }
}