package com.ilyaproject.order.controller.grpc.client;

import com.ilyaproject.api.OrderRequest;
import com.ilyaproject.api.OrderResponse;
import com.ilyaproject.api.OrderServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.grpc.client.ImportGrpcClients;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderClient {

    private final OrderServiceGrpc.OrderServiceBlockingStub blockingStub;
//    public OrderClient(){
//        ManagedChannel channel = ManagedChannelBuilder
//                .forTarget("cata  log")
//                .usePlaintext()
//                .build();
//        this.blockingStub = OrderServiceGrpc.newBlockingStub(channel);
//    }

    public boolean checkIfValidOrder(Long courseId, Long customerId){
        OrderRequest request = OrderRequest
                .newBuilder()
                .setCourseId(courseId)
                .setCustomerId(customerId)
                .build();
        OrderResponse response = blockingStub.orderValidation(request);
        return response.getIsValid();
    }
}
