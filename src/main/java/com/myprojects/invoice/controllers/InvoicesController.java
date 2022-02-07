package com.myprojects.invoice.controllers;

import com.myprojects.invoice.domain.dtos.InvoicesDto;
import com.myprojects.invoice.exceptions.*;
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
    public InvoicesDto getInvoice(@PathVariable("id") Long invoiceId) throws InvoicesNotFoundException {
        return invoicesFacade.getInvoice(invoiceId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/invoices", consumes = APPLICATION_JSON_VALUE)
    public InvoicesDto saveInvoice(@RequestBody InvoicesDto invoiceDto)
            throws InvoiceAlreadyExistsException {
        return invoicesFacade.saveInvoice(invoiceDto);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/invoices/{id}")
    public InvoicesDto updateInvoice(@PathVariable("id") Long invoiceId, @RequestBody InvoicesDto invoicesDto)
            throws InvoicesNotFoundException {
        return invoicesFacade.updateInvoice(invoiceId, invoicesDto);
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

    @RequestMapping(method = RequestMethod.PUT, value = "/invoices/{invoiceId}/add/{userId}")
    public InvoicesDto addUserToInvoice(@PathVariable("invoiceId") Long invoiceId,
                                           @PathVariable("userId") Long userId)
            throws InvoicesNotFoundException, UserNotFoundException {
        return invoicesFacade.addUserToInvoice(invoiceId, userId);
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

    @RequestMapping(method = RequestMethod.PUT, value = "/invoices/{invoiceId}/del/{userId}")
    public InvoicesDto deleteUserFromInvoice(@PathVariable("invoiceId") Long invoiceId,
                                                @PathVariable("userId") Long userId)
            throws InvoicesNotFoundException, UserNotFoundException {
        return invoicesFacade.deleteUserFromInvoice(invoiceId, userId);
    }
}