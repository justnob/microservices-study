package com.amarnath.orderservice.service;

import com.amarnath.orderservice.dto.requestDto.OrderListRequestDto;
import com.amarnath.orderservice.dto.requestDto.OrderRequestDto;
import com.amarnath.orderservice.dto.responseDto.ServerResponse;

public interface OrderService {

    public ServerResponse placeOrder(OrderRequestDto orderRequestDto);
    public ServerResponse getOrderList(OrderListRequestDto orderListRequestDto);
    public ServerResponse deleteOrderList(OrderListRequestDto orderListRequestDto);

    public ServerResponse deleteProductFromOrderList(OrderListRequestDto orderListRequestDto);

}
