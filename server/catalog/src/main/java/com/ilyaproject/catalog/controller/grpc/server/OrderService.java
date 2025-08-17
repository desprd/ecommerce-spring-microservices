package com.ilyaproject.catalog.controller.grpc.server;

import com.ilyaproject.api.OrderRequest;
import com.ilyaproject.api.OrderResponse;
import com.ilyaproject.api.OrderServiceGrpc;
import com.ilyaproject.catalog.exception.CourseNotFoundException;
import com.ilyaproject.catalog.repository.CourseRepository;
import com.ilyaproject.catalog.service.impl.CourseServiceImpl;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class OrderService extends OrderServiceGrpc.OrderServiceImplBase {

    private final CourseServiceImpl courseService;

    @Override
    public void orderValidation(OrderRequest request, StreamObserver<OrderResponse> responseObserver) {
        try {
            if (courseService.reserveCourse(request.getCourseId())){
                OrderResponse response = OrderResponse.newBuilder().setIsValid(true).build();
                responseObserver.onNext(response);
            }else {
                responseObserver.onError(Status.OUT_OF_RANGE.withDescription("No available spots left in course with id " + request.getCourseId()).asRuntimeException());
                responseObserver.onCompleted();
            }
        }catch (CourseNotFoundException e){
            responseObserver.onError(Status.NOT_FOUND.withDescription("Course with id " + request.getCourseId() + " was not found").asRuntimeException());
            responseObserver.onCompleted();
        }catch (Exception e){
            responseObserver.onError(Status.INTERNAL.withDescription("Reservation failed: " + e.getMessage()).asRuntimeException());
            responseObserver.onCompleted();
        }
        responseObserver.onCompleted();
    }
}
