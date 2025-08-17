package com.ilyaproject.order.mapper;

import com.ilyaproject.order.dto.write.CreateOrderDTO;
import com.ilyaproject.order.entity.Order;

public class OrderMapper {
    public static Order mapToOrder(CreateOrderDTO orderDTO){
        return Order
                .builder()
                .customerId(orderDTO.getCustomerId())
                .courseId(orderDTO.getCourseId())
                .build();
    }
}
