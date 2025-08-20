package com.ilyaproject.order.controller.grpc.service;


import com.ilyaproject.api.PaymentRequest;
import com.ilyaproject.api.PaymentResponse;
import com.ilyaproject.api.PaymentServiceGrpc;
import com.ilyaproject.order.exception.OrderWasNotFound;
import com.ilyaproject.order.service.impl.OrderServiceImpl;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.springframework.grpc.server.service.GrpcService;

import java.math.BigDecimal;

@GrpcService
@RequiredArgsConstructor
public class PaymentService extends PaymentServiceGrpc.PaymentServiceImplBase {

    private final OrderServiceImpl orderService;

    @Override
    public void paymentValidation(PaymentRequest request, StreamObserver<PaymentResponse> responseObserver) {
        try {
            orderService.isPaymentInformationValid(request.getCourseId(), request.getCustomerId(), new BigDecimal(request.getPrice()));
            PaymentResponse response = PaymentResponse
                    .newBuilder()
                    .setIsValid(true)
                    .build();
            responseObserver.onNext(response);
        }catch (OrderWasNotFound e){
            responseObserver.onError(Status.NOT_FOUND.withDescription(e.getMessage()).asRuntimeException());
            responseObserver.onCompleted();
        }catch (Exception e){
            responseObserver.onError(Status.INTERNAL.withDescription("Payment validation failed: " + e.getMessage()).asRuntimeException());
            responseObserver.onCompleted();
        }
        responseObserver.onCompleted();
    }

}
