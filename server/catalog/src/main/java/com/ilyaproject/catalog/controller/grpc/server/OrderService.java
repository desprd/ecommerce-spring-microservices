package com.ilyaproject.catalog.controller.grpc.server;

import com.ilyaproject.api.OrderRequest;
import com.ilyaproject.api.OrderResponse;
import com.ilyaproject.api.OrderServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
public class OrderService extends OrderServiceGrpc.OrderServiceImplBase {
    @Override
    public void orderValidation(OrderRequest request, StreamObserver<OrderResponse> responseObserver) {
        OrderResponse response = OrderResponse.newBuilder().setIsValid(true).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
