package com.myprojects.invoice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CeidgApi {

    private String fullName;
    private String street;
    private String building;
    private String local;
    private String town;
    private String postcode;
}