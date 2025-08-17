package com.ilyaproject.order.controller.grpc.client;

import com.ilyaproject.api.OrderRequest;
import com.ilyaproject.api.OrderResponse;
import com.ilyaproject.api.OrderServiceGrpc;
import com.ilyaproject.order.exception.OrderValidationFailedException;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.grpc.client.ImportGrpcClients;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderClient {

    private final OrderServiceGrpc.OrderServiceBlockingStub blockingStub;

    public boolean checkIfValidOrder(Long courseId, Long customerId){
        OrderRequest request = OrderRequest
                .newBuilder()
                .setCourseId(courseId)
                .setCustomerId(customerId)
                .build();
        try {
            OrderResponse response = blockingStub.orderValidation(request);
            return response.getIsValid();
        }catch (StatusRuntimeException e){
            var status = e.getStatus();
            throw new OrderValidationFailedException("Order validation failed with status " + status.getCode() + " and description: " + status.getDescription());
        }
    }
}
