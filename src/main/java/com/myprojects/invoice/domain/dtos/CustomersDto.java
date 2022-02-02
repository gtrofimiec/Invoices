package com.myprojects.invoice.domain.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
public class CustomersDto {

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
    private List<InvoicesDto> invoicesDtoList = new ArrayList<>();

    public CustomersDto(String fullName, String nip, String street, String postCode, String town) {
        this.fullName = fullName;
        this.nip = nip;
        this.street = street;
        this.postCode = postCode;
        this.town = town;
    }
}