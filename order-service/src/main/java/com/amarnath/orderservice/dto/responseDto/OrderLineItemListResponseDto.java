package com.amarnath.orderservice.dto.responseDto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineItemListResponseDto {

    private long id;
    private String skuCode;
    private String price;
    private int quantity;

}
