package com.myprojects.invoice.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CeidgApiDto {

    private String fullName;
    private String street;
    private String building;
    private String postCode;
    private String town;
}