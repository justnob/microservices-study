package com.amarnath.orderservice.dto.responseDto;

import com.amarnath.orderservice.model.OrderLineItem;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponseDto {

    private long id;
    private String orderNumber;
    private List<OrderLineItemListResponseDto> orderLineItemList;

}
