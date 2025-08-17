package com.ilyaproject.order.service;

import com.ilyaproject.order.dto.write.CreateOrderDTO;

public interface OrderService {
    void createService(CreateOrderDTO orderDTO);
}
