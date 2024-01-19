package com.amarnath.orderservice.repository;

import com.amarnath.orderservice.model.OrderLineItem;
import com.amarnath.orderservice.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderLineItemRepository extends JpaRepository<OrderLineItem, Long> {

    List<OrderLineItem> getByOrdersId(long id);


}
