package com.myprojects.invoice.domain.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "nazwa",
        "adresDzialanosci"
})
@Generated("jsonschema2pojo")
public class CustomerDataDto {

    @JsonProperty("nazwa")
    private String fullName;
    @JsonProperty("adresDzialanosci")
    private AddressDto addressDto;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
}