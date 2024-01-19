package com.amarnath.orderservice.dto.requestDto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderListRequestDto {

    private String orderNumber;
    private long productId;
}
