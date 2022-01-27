package com.myprojects.invoice.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name="invoices")
public class Invoices {

    public Invoices() {
        this.productsList = new ArrayList<>();
    }

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

    @NotNull
    @Column(name="net_sum")
    private BigDecimal netSum;

    @NotNull
    @Column(name="vat_sum")
    private BigDecimal vatSum;

    @NotNull
    @Column(name="gross_sum")
    private BigDecimal grossSum;

    @NotNull
    @Column(name="payment_method")
    private String paymentMethod;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customers customer;

    @ManyToMany(mappedBy = "invoicesList",
            cascade = CascadeType.ALL)
    private List<Products> productsList;

    @ManyToOne(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "user_id")
    private Users user;

    @Column(name = "deleted")
    private boolean deleted = false;
}