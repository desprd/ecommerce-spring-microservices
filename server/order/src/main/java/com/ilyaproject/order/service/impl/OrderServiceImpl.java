package com.ilyaproject.order.service.impl;

import com.ilyaproject.order.controller.grpc.client.OrderClient;
import com.ilyaproject.order.dto.write.CreateOrderDTO;
import com.ilyaproject.order.entity.Order;
import com.ilyaproject.order.exception.OrderAlreadyExistsException;
import com.ilyaproject.order.exception.OrderWasNotFound;
import com.ilyaproject.order.mapper.OrderMapper;
import com.ilyaproject.order.repository.OrderRepository;
import com.ilyaproject.order.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderClient orderClient;

    @Override
    @Transactional
    public void createService(CreateOrderDTO orderDTO) {
        if (checkIfOrderIsPresent(orderDTO)){
            throw new OrderAlreadyExistsException("User with id " + orderDTO.getCustomerId() + " already ordered course with id " + orderDTO.getCourseId());
        }
        checkIfOrderIsValid(orderDTO);
        orderRepository.save(OrderMapper.mapToOrder(orderDTO));
    }

    @Override
    public void isPaymentInformationValid(Long orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isEmpty()){
            throw new OrderWasNotFound(String.format("Order with id %s was not found", orderId));
        }
    }

    private Boolean checkIfOrderIsValid(CreateOrderDTO orderDTO){
        return orderClient.checkIfValidOrder(orderDTO.getCourseId(), orderDTO.getCustomerId(), orderDTO.getPrice());
    }

    private Boolean checkIfOrderIsPresent(CreateOrderDTO orderDTO){
        Optional<Order> order = orderRepository.findSameOrder(orderDTO.getCustomerId(), orderDTO.getCourseId());
        if (order.isPresent()){
            return true;
        }else {
            return false;
        }
    }
}
