package com.amarnath.orderservice.mapper;

import com.amarnath.orderservice.dto.requestDto.OrderLineItemDto;
import com.amarnath.orderservice.dto.responseDto.OrderLineItemListResponseDto;
import com.amarnath.orderservice.dto.responseDto.OrderResponseDto;
import com.amarnath.orderservice.model.Orders;
import com.amarnath.orderservice.model.OrderLineItem;

import java.util.List;

public class OrderMapper {

    public static OrderLineItem mapToOrderLineItem(OrderLineItemDto orderLineItemDto){
        return OrderLineItem.builder()
                .price(orderLineItemDto.getPrice())
                .skuCode(orderLineItemDto.getSkuCode())
                .quantity(orderLineItemDto.getQuantity())
                .build();
    }

    public static OrderResponseDto mapToOrderResponseDto(Orders orders, List<OrderLineItem> orderLineItemList){

        List<OrderLineItemListResponseDto> listOfOrder = orderLineItemList.stream().map(OrderMapper::mapToOderLineItemListDto).toList();

        return OrderResponseDto.builder()
                .id(orders.getId())
                .orderNumber(orders.getOrderNumber())
                .orderLineItemList(listOfOrder)
                .build();
    }

    public static OrderLineItemListResponseDto mapToOderLineItemListDto(OrderLineItem orderLineItemList){

        return OrderLineItemListResponseDto.builder()
                .id(orderLineItemList.getId())
                .price(orderLineItemList.getPrice())
                .quantity(orderLineItemList.getQuantity())
                .skuCode(orderLineItemList.getSkuCode())
                .build();

    }

}
