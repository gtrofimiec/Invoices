package com.myprojects.invoice.domain.dtos;

import lombok.Data;

import java.util.List;

@Data
public class UserDto {

    private Long id;
    private String fullName;
    private String nip;
    private String street;
    private String postCode;
    private String town;
    private List<InvoicesDto> invoicesDtoList;
}