package com.myprojects.invoice.domain.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CeidgApiDataListDto {

    @JsonProperty("firma")
    private List<CustomerDataDto> customerDataDto = null;
}