package com.myprojects.invoice.mappers;

import com.myprojects.invoice.domain.Invoices;
import com.myprojects.invoice.domain.dtos.InvoicesDto;
import com.myprojects.invoice.exceptions.InvoicesNotFoundException;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoicesMapper {

    public Invoices mapToInvoice(final @NotNull InvoicesDto invoiceDto) throws InvoicesNotFoundException {
        CustomersMapper customersMapper = new CustomersMapper();
        ProductsMapper productsMapper = new ProductsMapper();
        UserMapper userMapper = new UserMapper();
        Invoices invoice = new Invoices();
            invoice.setId(invoiceDto.getId());
            invoice.setNumber(invoiceDto.getNumber());
            invoice.setDate(invoiceDto.getDate());
            invoice.setCustomer(customersMapper.mapToCustomer(invoiceDto.getCustomerDto()));
            invoice.setProductsList(productsMapper.mapToProductsList(invoiceDto.getProductsDtoList()));
            invoice.setUser(userMapper.mapToUser(invoiceDto.getUserDto()));
        return invoice;
    }

    public InvoicesDto mapToInvoiceDto(final @NotNull Invoices invoice) {
        CustomersMapper customersMapper = new CustomersMapper();
        ProductsMapper productsMapper = new ProductsMapper();
        UserMapper userMapper = new UserMapper();
        InvoicesDto invoiceDto = new InvoicesDto();
        invoiceDto.setId(invoice.getId());
            invoiceDto.setNumber(invoice.getNumber());
            invoiceDto.setDate(invoice.getDate());
            invoiceDto.setCustomerDto(customersMapper.mapToCustomerDto(invoice.getCustomer()));
            invoiceDto.setProductsDtoList(productsMapper.mapToProductsDtoList(invoice.getProductsList()));
            invoiceDto.setUserDto(userMapper.mapToUserDto(invoice.getUser()));
        return invoiceDto;
    }

    public List<InvoicesDto> mapToInvoicesDtoList(final @NotNull List<Invoices> invoicesList) {
        return invoicesList.stream()
                .map(this::mapToInvoiceDto)
                .collect(Collectors.toList());
    }
}