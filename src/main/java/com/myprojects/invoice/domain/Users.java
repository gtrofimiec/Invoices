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
@Table(name="users")
public class Users {

    public Users(String fullName, String nip, String street, String postCode, String town) {
        this.fullName = fullName;
        this.nip = nip;
        this.street = street;
        this.postCode = postCode;
        this.town = town;
        this.invoicesList = new ArrayList<>();
    }

    @Id
    @GeneratedValue
    @NotNull
    @Column(name="user_id")
    private Long id;

    @NotNull
    @Column(name="full_name")
    private String fullName;

    @NotNull
    @Column(name="nip")
    private String nip;

    @NotNull
    @Column(name="street")
    private String street;

    @NotNull
    @Column(name="postcode")
    private String postCode;

    @NotNull
    @Column(name="town")
    private String town;

    @NotNull
    @Column(name = "active")
    private boolean active = false;

    @OneToMany(targetEntity = Invoices.class,
            mappedBy = "user",
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.REFRESH,
                    CascadeType.PERSIST
            }, fetch = FetchType.EAGER)
    private List<Invoices> invoicesList;

    @Column(name = "deleted")
    private boolean deleted = false;
}