package com.amarnath.orderservice.service.impl;

import com.amarnath.orderservice.dto.requestDto.OrderListRequestDto;
import com.amarnath.orderservice.dto.requestDto.OrderRequestDto;
import com.amarnath.orderservice.dto.responseDto.OrderResponseDto;
import com.amarnath.orderservice.dto.responseDto.ServerResponse;
import com.amarnath.orderservice.mapper.OrderMapper;
import com.amarnath.orderservice.model.Orders;
import com.amarnath.orderservice.model.OrderLineItem;
import com.amarnath.orderservice.repository.OrderLineItemRepository;
import com.amarnath.orderservice.repository.OrderRepository;
import com.amarnath.orderservice.service.OrderService;
import com.amarnath.orderservice.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderLineItemRepository orderLineItemRepository;

    @Override
    public ServerResponse placeOrder(OrderRequestDto orderRequestDto) {

        Orders orders = Orders.builder()
                .orderNumber(UUID.randomUUID().toString())
                .build();
        Orders savedOrders = orderRepository.save(orders);
        log.debug("Order saved in the database");

        List<OrderLineItem> listOfOrder = orderRequestDto.getOrderLineItemDto().stream().map(OrderMapper::mapToOrderLineItem).toList();

        listOfOrder.forEach(listOfOrders ->{
            listOfOrders.setOrders(savedOrders);
            orderLineItemRepository.save(listOfOrders);
            log.debug("Order list saved in the database");
        });

        List<OrderLineItem> allOrderListByOrderId = orderLineItemRepository.getByOrdersId(savedOrders.getId());

        OrderResponseDto orderResponseDto = OrderMapper.mapToOrderResponseDto(savedOrders, allOrderListByOrderId);
        log.debug("Order save in DB: {}", JsonUtil.toString(orderResponseDto));
        return ServerResponse.builder()
                .code(1)
                .message("Order Placed Successfully!")
                .success(true)
                .data(orderResponseDto)
                .build();
    }

    @Override
    public ServerResponse getOrderList(OrderListRequestDto orderListRequestDto) {

        Optional<Orders> byOrderNumber = orderRepository.getByOrderNumber(orderListRequestDto.getOrderNumber());
        log.debug("Order fetched from the database");

        if (byOrderNumber.isPresent()){
            List<OrderLineItem> orderListByOrdersId = orderLineItemRepository.getByOrdersId(byOrderNumber.get().getId());
            log.debug("Order list fetched from the database");

            OrderResponseDto orderResponseDto = OrderMapper.mapToOrderResponseDto(byOrderNumber.get(), orderListByOrdersId);

            log.debug("Order List from DB: {}", JsonUtil.toString(orderResponseDto));
            return ServerResponse.builder()
                    .success(true)
                    .code(1)
                    .message("Order List Fetch Successfully!")
                    .data(orderResponseDto)
                    .build();
        }else {
            return ServerResponse.builder()
                    .success(false)
                    .code(2)
                    .message("Order List Not Present!")
                    .build();
        }

    }

    @Override
    public ServerResponse deleteOrderList(OrderListRequestDto orderListRequestDto) {
        Optional<Orders> byOrderNumber = orderRepository.getByOrderNumber(orderListRequestDto.getOrderNumber());
        if (byOrderNumber.isPresent()){
            List<OrderLineItem> byOrdersId = orderLineItemRepository.getByOrdersId(byOrderNumber.get().getId());
            byOrdersId.forEach(data -> orderLineItemRepository.deleteById(data.getId()));
            log.debug("Product's related with this order has been deleted!");
            orderRepository.deleteById(byOrderNumber.get().getId());
            log.debug("Order Deleted Successfully!");
            return ServerResponse.builder()
                    .message("Order Deleted Successfully!")
                    .code(1)
                    .success(true)
                    .build();
        }else {
            return ServerResponse.builder()
                    .message("Order Not Found!")
                    .code(2)
                    .success(false)
                    .build();
        }
    }

    @Override
    public ServerResponse deleteProductFromOrderList(OrderListRequestDto orderListRequestDto) {
        Optional<Orders> byOrderNumber = orderRepository.getByOrderNumber(orderListRequestDto.getOrderNumber());
        if (byOrderNumber.isPresent()){
            log.debug("Order fetched from database");
            Optional<OrderLineItem> productById = orderLineItemRepository.findById(orderListRequestDto.getProductId());
            if (productById.isPresent()){
                log.debug("Product fetched from database");
                orderLineItemRepository.deleteById(orderListRequestDto.getProductId());
                log.debug("Product Deleted Successfully!");
                return ServerResponse.builder()
                        .message("Product Deleted Successfully!")
                        .code(1)
                        .success(true)
                        .build();
            }else {
                return ServerResponse.builder()
                        .message("Product Not Found!")
                        .code(2)
                        .success(false)
                        .build();
            }
        }else {
            return ServerResponse.builder()
                    .message("Order Not Found!")
                    .code(2)
                    .success(false)
                    .build();
        }
    }
}
