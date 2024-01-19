package com.amarnath.orderservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "ORDER_LINE_ITEM")
public class OrderLineItem {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "SKU_CODE")
    private String skuCode;

    @Column(name = "PRICE")
    private String price;

    @Column(name = "QUANTITY")
    private int quantity;

    @ManyToOne
    @JoinColumn(name="ORDERS_ID", nullable=false)
    private Orders orders;

}
