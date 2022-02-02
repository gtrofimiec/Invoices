package com.myprojects.invoice.domain.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
public class InvoicesDto {

    @JsonProperty
    private Long id;
    @JsonProperty
    private String number;
    @JsonProperty
    private LocalDateTime date;
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

    public InvoicesDto(String number, LocalDateTime date, BigDecimal netSum, BigDecimal vatSum, BigDecimal grossSum,
                       String paymentMethod) {
        this.number = number;
        this.date = date;
        this.netSum = netSum;
        this.vatSum = vatSum;
        this.grossSum = grossSum;
        this.paymentMethod = paymentMethod;
    }
}