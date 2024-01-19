package com.amarnath.orderservice.dto.requestDto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDto {

    private List<OrderLineItemDto> orderLineItemDto;

}
