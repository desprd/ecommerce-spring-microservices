package com.ilyaproject.order.listener;

import com.ilyaproject.order.dto.write.PaymentResultDTO;
import com.ilyaproject.order.service.impl.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderGroupListeners {

    private final OrderServiceImpl orderService;

    @KafkaListener(topics = {
            "${app.kafka.topics.payment-succeeded}",
            "${app.kafka.topics.payment-failed}"
    }, groupId = "${spring.kafka.consumer.group-id}")
    public void paymentListener(PaymentResultDTO resultDTO){
        orderService.changeOrderStatusAfterPayment(resultDTO.getOrderId(), resultDTO.getIsSucceeded());
    }
}
