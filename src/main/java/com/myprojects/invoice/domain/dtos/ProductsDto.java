package com.myprojects.invoice.domain.dtos;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductsDto {

    private Long id;
    private String name;
    private int vatRate;
    private BigDecimal netPrice;
    private BigDecimal vatValue;
    private BigDecimal grossPrice;
    private List<InvoicesDto> invoicesDtoList;
}