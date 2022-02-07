package com.myprojects.invoice.domain.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerDataDto {

    @JsonProperty("nazwa")
    private String fullName;
    @JsonProperty("adresDzialanosci")
    private AddressDto addressDto;
}