package com.myprojects.invoice.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Table(name="products")
public class Products {

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
            CascadeType.REFRESH,
            CascadeType.DETACH
    },
            fetch = FetchType.LAZY)
    @JoinTable(name ="invoices_has_products",
            joinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "product_id")},
            inverseJoinColumns = {@JoinColumn(name = "invoice_id", referencedColumnName = "invoice_id")}
    )
    private List<Invoices> invoicesList;

    @Column(name = "deleted")
    private boolean deleted = false;
}