package com.ilyaproject.message.listener;

import com.ilyaproject.message.dto.PaymentResultDTO;
import com.ilyaproject.message.message.PaymentMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderGroupListeners {
    
    private final PaymentMessage message;
    
    @KafkaListener(topics = "${app.kafka.topics.message-success}", groupId = "${spring.kafka.consumer.group-id}")
    public void messageListener(PaymentResultDTO paymentResultDTO){
        if (paymentResultDTO.getIsSucceeded()){
            message.sendPaymentSuccessMessage();
        }
    }
}
