package com.ilyaproject.order.service;

import com.ilyaproject.order.dto.read.ResponseOrderDTO;
import com.ilyaproject.order.dto.write.CreateOrderDTO;

import java.math.BigDecimal;

public interface OrderService {
    void createService(CreateOrderDTO orderDTO);

    void isPaymentInformationValid(Long orderId);

    void changeOrderStatusAfterPayment(Long orderId, boolean success);

    ResponseOrderDTO fetchOrderById(Long id);
}
