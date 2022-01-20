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
        "ulica",
        "budynek",
        "miasto",
        "kod"
})
@Generated("jsonschema2pojo")
public class AddressDto {

    @JsonProperty("ulica")
    private String street;
    @JsonProperty("budynek")
    private String building;
    @JsonProperty("miasto")
    private String town;
    @JsonProperty("kod")
    private String postCode;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
}