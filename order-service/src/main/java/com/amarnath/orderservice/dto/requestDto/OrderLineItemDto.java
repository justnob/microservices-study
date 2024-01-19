package com.amarnath.orderservice.dto.requestDto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderLineItemDto {

    private String skuCode;
    private String price;
    private int quantity;

}
