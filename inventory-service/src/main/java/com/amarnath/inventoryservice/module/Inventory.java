package com.amarnath.inventoryservice.module;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "INVENTORY")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @Column(name = "PRODUCT_CODE")
    private String skuCode;

    @Column(name = "QUANTITY")
    private int quantity;

}
