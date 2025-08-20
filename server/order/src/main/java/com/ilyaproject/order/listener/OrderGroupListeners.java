package com.ilyaproject.order.listener;

import com.ilyaproject.order.dto.write.PaymentResultDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderGroupListeners {

    @KafkaListener(topics = "payments.succeeded.v1", groupId = "${spring.kafka.consumer.group-id}")
    public void paymentListener(PaymentResultDTO resultDTO){
        System.out.println(resultDTO.getOrderId());
    }
}
