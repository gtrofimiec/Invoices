package com.myprojects.invoice.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="products")
public class Products {

//    public Products(String name, int vatRate, BigDecimal netPrice, BigDecimal vatValue, BigDecimal grossPrice) {
//        this.name = name;
//        this.vatRate = vatRate;
//        this.netPrice = netPrice;
//        this.vatValue = vatValue;
//        this.grossPrice = grossPrice;
//        this.invoicesList = new ArrayList<>();
//    }

    @Id
    @GeneratedValue
    @NotNull
    @Column(name="product_id")
    private Long id;

    @NotNull
    @Column(name="name")
    private String name;

    @NotNull
    @Column(name="vat_rate")
    private int vatRate;

    @NotNull
    @Column(name="net_price")
    private BigDecimal netPrice;

    @NotNull
    @Column(name="vat_value")
    private BigDecimal vatValue;

    @NotNull
    @Column(name="gross_price")
    private BigDecimal grossPrice;

    @ManyToMany(cascade = {
            CascadeType.DETACH,
            CascadeType.REFRESH,
            CascadeType.PERSIST
    }, fetch = FetchType.EAGER)
    @JoinTable(name ="invoices_has_products",
            joinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "product_id")},
            inverseJoinColumns = {@JoinColumn(name = "invoice_id", referencedColumnName = "invoice_id")}
    )
    private List<Invoices> invoicesList;

    @Column(name = "deleted")
    private boolean deleted = false;
}