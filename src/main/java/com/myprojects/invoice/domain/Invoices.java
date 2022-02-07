package com.myprojects.invoice.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
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
            CascadeType.MERGE
    }, fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customers customer;

    @ManyToMany(cascade = {
            CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.MERGE
    }, fetch = FetchType.EAGER)
    @JoinTable(name ="invoices_has_products",
            joinColumns = {@JoinColumn(name = "invoice_id", referencedColumnName = "invoice_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "product_id")}
    )
    private List<Products> productsList;

    @ManyToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.REFRESH,
            CascadeType.MERGE
    }, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private Users user;

    @Column(name = "deleted")
    private boolean deleted = false;

    public Invoices(String number, Date date, BigDecimal netSum, BigDecimal vatSum, BigDecimal grossSum,
                    String paymentMethod) {
        this.number = number;
        this.date = date;
        this.netSum = netSum;
        this.vatSum = vatSum;
        this.grossSum = grossSum;
        this.paymentMethod = paymentMethod;
        this.productsList = new ArrayList<>();
    }
}