package com.ilyaproject.payment.controller.grpc;

import com.ilyaproject.api.PaymentRequest;
import com.ilyaproject.api.PaymentResponse;
import com.ilyaproject.api.PaymentServiceGrpc;
import com.ilyaproject.payment.dto.write.PaymentResultDTO;
import com.ilyaproject.payment.exception.PaymentDetailsValidationFailedException;
import io.grpc.StatusRuntimeException;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class PaymentClient {

    private final PaymentServiceGrpc.PaymentServiceBlockingStub blockingStub;
    private final KafkaTemplate<String, PaymentResultDTO> kafkaTemplate;

    public boolean checkPaymentDetails(Long orderId){
        PaymentRequest request = PaymentRequest
                .newBuilder()
                .setOrderId(orderId)
                .build();
        try {
            PaymentResponse response = blockingStub.paymentValidation(request);
            return response.getIsValid();
        }catch (StatusRuntimeException e){
            var status = e.getStatus();
            throw new PaymentDetailsValidationFailedException("Payment validation failed with status " + status.getCode() + " and description: " + status.getDescription());
        }
    }
}
