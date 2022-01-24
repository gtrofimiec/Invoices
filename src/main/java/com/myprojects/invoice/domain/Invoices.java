package com.myprojects.invoice.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name="invoices")
public class Invoices {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name="invoice_id")
    private Long id;

    @NotNull
    @Column(name="number")
    private String number;

    @NotNull
    @Column(name="date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customers customer;

    @ManyToMany(mappedBy = "invoicesList",
            cascade = {
                CascadeType.REFRESH,
                CascadeType.DETACH,
                CascadeType.REMOVE
            },
            fetch = FetchType.LAZY)
    private List<Products> productsList;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @Column(name = "deleted")
    private boolean deleted = false;
}