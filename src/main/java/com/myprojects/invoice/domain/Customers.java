package com.myprojects.invoice.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name="customers")
public class Customers {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name="customer_id")
    private Long id;

    @NotNull
    @Column(name="full_name")
    private String fullName;

    @Column(name="nip")
    private String nip;

    @Column(name="street")
    private String street;

    @Column(name="postcode")
    private String postCode;

    @Column(name="town")
    private String town;

    @OneToMany(targetEntity = Invoices.class,
            mappedBy = "customer",
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.REMOVE},
            fetch = FetchType.LAZY)
    private List<Invoices> invoicesList;

    @Column(name = "deleted")
    private boolean deleted = false;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customers customers = (Customers) o;
        return deleted == customers.deleted && id.equals(customers.id) && fullName.equals(customers.fullName) && nip.equals(customers.nip) && street.equals(customers.street) && postCode.equals(customers.postCode) && town.equals(customers.town) && invoicesList.equals(customers.invoicesList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, nip, street, postCode, town, invoicesList, deleted);
    }
}