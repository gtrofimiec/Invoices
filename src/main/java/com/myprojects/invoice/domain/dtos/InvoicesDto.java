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

    @JsonProperty("invoice_id")
    private Long id;
    @JsonProperty("number")
    private String number;
    @JsonProperty("date")
    private Date date;
    @JsonProperty("net_sum")
    private BigDecimal netSum;
    @JsonProperty("vat_sum")
    private BigDecimal vatSum;
    @JsonProperty("gross_sum")
    private BigDecimal grossSum;
    @JsonProperty("payment_method")
    private String paymentMethod;
    @JsonProperty("customer_id")
    private CustomersDto customerDto;
    @JsonProperty("products_list")
    private List<ProductsDto> productsDtoList = new ArrayList<>();
    @JsonProperty("user_id")
    private UsersDto userDto;
}