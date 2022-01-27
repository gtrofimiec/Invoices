package com.myprojects.invoice.domain.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class ProductsDto {

    @JsonProperty("product_id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("vat_rate")
    private int vatRate;
    @JsonProperty("net_price")
    private BigDecimal netPrice;
    @JsonProperty("vat_value")
    private BigDecimal vatValue;
    @JsonProperty("gross_price")
    private BigDecimal grossPrice;
    @JsonProperty("invoices_list")
    private List<InvoicesDto> invoicesDtoList = new ArrayList<>();
}