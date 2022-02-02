package com.myprojects.invoice.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name="invoices")
public class Invoices {

    public Invoices(String number, LocalDateTime date, BigDecimal netSum, BigDecimal vatSum, BigDecimal grossSum,
                    String paymentMethod) {
        this.number = number;
        this.date = date;
        this.netSum = netSum;
        this.vatSum = vatSum;
        this.grossSum = grossSum;
        this.paymentMethod = paymentMethod;
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
    private LocalDateTime date;

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

    @ManyToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.REFRESH,
            CascadeType.PERSIST
    }, fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customers customer;

    @ManyToMany(mappedBy = "invoicesList",
            cascade = {
            CascadeType.DETACH,
            CascadeType.REFRESH,
            CascadeType.PERSIST
    }, fetch = FetchType.EAGER)
    private List<Products> productsList;

    @ManyToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.REFRESH,
            CascadeType.PERSIST
    }, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private Users user;

    @Column(name = "deleted")
    private boolean deleted = false;
}