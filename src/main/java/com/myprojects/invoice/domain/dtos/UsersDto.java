package com.myprojects.invoice.domain.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
public class UsersDto {

    @JsonProperty
    private Long id;
    @JsonProperty
    private String fullName;
    @JsonProperty
    private String nip;
    @JsonProperty
    private String street;
    @JsonProperty
    private String postCode;
    @JsonProperty
    private String town;
    @JsonProperty
    private String bank;
    @JsonProperty
    private String accountNumber;
    @JsonProperty
    private String pdfPath;
    @JsonProperty
    private String userLogin;
    @JsonProperty
    private String userPass;
    @JsonProperty
    private boolean active;
    @JsonProperty
    private List<InvoicesDto> invoicesDtoList = new ArrayList<>();
}