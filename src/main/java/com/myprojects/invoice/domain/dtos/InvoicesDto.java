package com.myprojects.invoice.domain.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Data
public class InvoicesDto {

    @JsonProperty
    private Long id;
    @JsonProperty
    private String number;
    @JsonProperty
    private Date date;
    @JsonProperty
    private BigDecimal netSum;
    @JsonProperty
    private BigDecimal vatSum;
    @JsonProperty
    private BigDecimal grossSum;
    @JsonProperty
    private String paymentMethod;
    @JsonProperty
    private CustomersDto customerDto;
    @JsonProperty
    private List<ProductsDto> productsDtoList;
    @JsonProperty
    private UsersDto userDto;
}