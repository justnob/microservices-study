package com.amarnath.inventoryservice.dto.requestDto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductCreateRequest {

    private String skuCode;
    private int quantity;
}
