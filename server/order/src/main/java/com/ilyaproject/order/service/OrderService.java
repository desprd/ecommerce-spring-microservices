package com.ilyaproject.order.service;

import com.ilyaproject.order.dto.write.CreateOrderDTO;

import java.math.BigDecimal;

public interface OrderService {
    void createService(CreateOrderDTO orderDTO);

    void isPaymentInformationValid(Long courseId, Long customerId, BigDecimal price);

}
