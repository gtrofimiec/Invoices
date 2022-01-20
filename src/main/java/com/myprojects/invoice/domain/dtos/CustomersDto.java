package com.myprojects.invoice.domain.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Objects;

@Data
public class CustomersDto {

    @JsonProperty("customer_id")
    private Long id;
    @JsonProperty("full_name")
    private String fullName;
    @JsonProperty("nip")
    private String nip;
    @JsonProperty("street")
    private String street;
    @JsonProperty("postcode")
    private String postcode;
    @JsonProperty("town")
    private String town;
    private List<InvoicesDto> invoicesDtoList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomersDto that = (CustomersDto) o;
        return id.equals(that.id) && fullName.equals(that.fullName) && nip.equals(that.nip) && street.equals(that.street) && postcode.equals(that.postcode) && town.equals(that.town) && invoicesDtoList.equals(that.invoicesDtoList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, nip, street, postcode, town, invoicesDtoList);
    }
}