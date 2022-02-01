package com.myprojects.invoice.domain.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private List<ProductsDto> productsDtoList = new ArrayList<>();
    @JsonProperty
    private UsersDto userDto;
}