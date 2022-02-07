package com.myprojects.invoice.domain.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressDto {

    @JsonProperty("ulica")
    private String street;
    @JsonProperty("budynek")
    private String building;
    @JsonProperty("miasto")
    private String town;
    @JsonProperty("kod")
    private String postCode;
}