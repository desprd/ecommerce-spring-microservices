package com.ilyaproject.payment.service.impl;

import com.ilyaproject.payment.controller.grpc.PaymentClient;
import com.ilyaproject.payment.dto.write.PaymentInformationDTO;
import com.ilyaproject.payment.dto.write.PaymentResultDTO;
import com.ilyaproject.payment.exception.WrongPaymentAmountException;
import com.ilyaproject.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentClient paymentClient;
    private final KafkaTemplate<String, PaymentResultDTO> kafkaTemplate;


    @Override
    public void pay(PaymentInformationDTO paymentInformationDTO) {
        if (!paymentInformationDTO.getPrice().equals(paymentInformationDTO.getPaidAmount())){
            throw new WrongPaymentAmountException("Wrong amount! User paid " + paymentInformationDTO.getPaidAmount() + " when the course is " + paymentInformationDTO.getPrice());
        }
        paymentClient.checkPaymentDetails(paymentInformationDTO.getOrderId());
        kafkaTemplate.send("payments.succeeded.v1", new PaymentResultDTO(paymentInformationDTO.getOrderId(), true));
    }
}
