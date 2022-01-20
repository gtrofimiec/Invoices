package com.myprojects.invoice.domain.dtos;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class InvoicesDto {

    private Long id;
    private String number;
    private Date date;
    private CustomersDto customerDto;
    private List<ProductsDto> productsDtoList;
    private UserDto userDto;
}