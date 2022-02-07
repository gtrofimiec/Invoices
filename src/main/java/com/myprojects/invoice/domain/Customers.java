package com.myprojects.invoice.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
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
                    CascadeType.DETACH,
                    CascadeType.REFRESH,
                    CascadeType.PERSIST
            },
            fetch = FetchType.EAGER)
    private List<Invoices> invoicesList;

    @Column(name = "deleted")
    private boolean deleted = false;

    public Customers(String fullName, String nip, String street, String postCode, String town) {
        this.fullName = fullName;
        this.nip = nip;
        this.street = street;
        this.postCode = postCode;
        this.town = town;
        this.invoicesList = new ArrayList<>();
    }
}