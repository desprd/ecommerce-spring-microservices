package com.ilyaproject.order.config;

import com.ilyaproject.api.OrderServiceGrpc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.grpc.client.GrpcChannelFactory;

@Configuration
public class GrpcConfiguration {
    @Bean
    OrderServiceGrpc.OrderServiceBlockingStub stub(GrpcChannelFactory channels){
        return OrderServiceGrpc.newBlockingStub(channels.createChannel("catalog"));
    }
}
