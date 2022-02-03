package com.myprojects.invoice.domain.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
//@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonPropertyOrder({
//        "ulica",
//        "budynek",
//        "miasto",
//        "kod"
//})
public class AddressDto {

    @JsonProperty("ulica")
    private String street;
    @JsonProperty("budynek")
    private String building;
    @JsonProperty("miasto")
    private String town;
    @JsonProperty("kod")
    private String postCode;
//    @JsonIgnore
//    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
}