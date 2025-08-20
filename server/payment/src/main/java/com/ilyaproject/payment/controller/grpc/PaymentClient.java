package com.ilyaproject.payment.controller.grpc;

import com.ilyaproject.api.PaymentRequest;
import com.ilyaproject.api.PaymentResponse;
import com.ilyaproject.api.PaymentServiceGrpc;
import com.ilyaproject.payment.exception.PaymentDetailsValidationFailedException;
import io.grpc.StatusRuntimeException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class PaymentClient {

    private final PaymentServiceGrpc.PaymentServiceBlockingStub blockingStub;

    public boolean checkPaymentDetails(Long courseId, Long customerId, BigDecimal price){
        PaymentRequest request = PaymentRequest
                .newBuilder()
                .setCustomerId(customerId)
                .setCourseId(courseId)
                .setPrice(price.toPlainString())
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
