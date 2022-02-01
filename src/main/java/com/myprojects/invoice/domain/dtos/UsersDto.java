package com.myprojects.invoice.domain.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

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
    private boolean active;
    @JsonProperty
    private List<InvoicesDto> invoicesDtoList = new ArrayList<>();
}