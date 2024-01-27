package com.amarnath.orderservice.dto.responseDto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventoryStockResponse {

    private String skuCode;
    private boolean isPresent;
}
