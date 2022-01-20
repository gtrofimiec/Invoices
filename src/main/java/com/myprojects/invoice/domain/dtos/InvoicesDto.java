package com.myprojects.invoice.domain.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class InvoicesDto {

    @JsonProperty("invoice_id")
    private Long id;
    @JsonProperty("number")
    private String number;
    @JsonProperty("date")
    private Date date;
    private CustomersDto customerDto;
    private List<ProductsDto> productsDtoList;
    private UserDto userDto;
}