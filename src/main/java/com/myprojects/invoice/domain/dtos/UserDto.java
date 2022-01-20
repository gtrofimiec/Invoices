package com.myprojects.invoice.domain.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {

    @JsonProperty("user_id")
    private Long id;
    @JsonProperty("full_name")
    private String fullName;
    @JsonProperty("nip")
    private String nip;
    @JsonProperty("street")
    private String street;
    @JsonProperty("postcode")
    private String postCode;
    @JsonProperty("town")
    private String town;
    private List<InvoicesDto> invoicesDtoList;
}