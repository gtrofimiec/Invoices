package com.myprojects.invoice.domain.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostcodeApiDto {

    @JsonProperty("miejscowosc")
    private String town;
    @JsonProperty("gmina")
    private String districtCommune;
}