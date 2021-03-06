package com.myprojects.invoice.domain.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
public class ProductsDto {

    @JsonProperty
    private Long id;
    @JsonProperty
    private String name;
    @JsonProperty
    private String pkwiu;
    @JsonProperty
    private String measureUnit;
    @JsonProperty
    private int vatRate;
    @JsonProperty
    private BigDecimal netPrice;
    @JsonProperty
    private BigDecimal vatValue;
    @JsonProperty
    private BigDecimal grossPrice;
    @JsonProperty
    private List<InvoicesDto> invoicesDtoList = new ArrayList<>();
}