package com.ilyaproject.order.mapper;

import com.ilyaproject.order.dto.read.ResponseOrderDTO;
import com.ilyaproject.order.dto.write.CreateOrderDTO;
import com.ilyaproject.order.entity.Order;

public class OrderMapper {
    public static Order mapToOrder(CreateOrderDTO orderDTO){
        return Order
                .builder()
                .customerId(orderDTO.getCustomerId())
                .courseId(orderDTO.getCourseId())
                .price(orderDTO.getPrice())
                .build();
    }

    public static ResponseOrderDTO mapToResponseOrderDTO(Order order){
        return ResponseOrderDTO
                .builder()
                .orderId(order.getOrderId())
                .courseId(order.getCourseId())
                .customerId(order.getCustomerId())
                .price(order.getPrice())
                .status(order.getStatus().name())
                .build();
    }
}
