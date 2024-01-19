package com.amarnath.orderservice.controller;

import com.amarnath.orderservice.dto.requestDto.OrderListRequestDto;
import com.amarnath.orderservice.dto.requestDto.OrderRequestDto;
import com.amarnath.orderservice.dto.responseDto.ServerResponse;
import com.amarnath.orderservice.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("v1/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /***
     * METHOD POST
     * @param orderRequestDto
     * RETURN the response of the order has been placed or not as requested.
     */
    @PostMapping("/place")
    public ResponseEntity<?> placeOrder(@RequestBody OrderRequestDto orderRequestDto){
        log.debug("Entering order placing api.......");
        ServerResponse orderPlacedResponse = orderService.placeOrder(orderRequestDto);
        return new ResponseEntity<>(orderPlacedResponse, HttpStatus.CREATED);
    }

    /***
     * METHOD POST
     * @param orderListRequestDto
     * RETURN the response with data if the  order list is present in the database by the order number provided by the user.
     */
    @PostMapping("/list")
    public ResponseEntity<?> getOrderListByOrderNumber(@RequestBody OrderListRequestDto orderListRequestDto){
        log.debug("Entering order list api........");
        ServerResponse orderListDetail = orderService.getOrderList(orderListRequestDto);
        return  new ResponseEntity<>(orderListDetail, HttpStatus.OK);
    }

    /***
     * METHOD POST
     * @param orderListRequestDto
     * RETURN the response of the deletion of all product order from the database is successful or not.
     */
    @PostMapping("/delete")
    public ResponseEntity<?> deleteOrderListByOrderNumber(@RequestBody OrderListRequestDto orderListRequestDto){
        log.debug("Entering order list delete api........");
        ServerResponse orderListDeleted = orderService.deleteOrderList(orderListRequestDto);
        return  new ResponseEntity<>(orderListDeleted, HttpStatus.OK);
    }

    /***
     * METHOD POST
     * @param orderListRequestDto
     * RETURN the response of the deletion of a single product order from the database is successful or not.
     */
    @PostMapping("/product/delete")
    public ResponseEntity<?> deleteProductFromOrderList(@RequestBody OrderListRequestDto orderListRequestDto){
        log.debug("Entering product delete api........");
        ServerResponse productFromOrderListDeleted = orderService.deleteProductFromOrderList(orderListRequestDto);
        return  new ResponseEntity<>(productFromOrderListDeleted, HttpStatus.OK);
    }

}
