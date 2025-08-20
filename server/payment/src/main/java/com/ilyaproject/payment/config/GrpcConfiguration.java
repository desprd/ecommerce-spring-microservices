package com.ilyaproject.payment.config;

import com.ilyaproject.api.PaymentServiceGrpc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.grpc.client.GrpcChannelFactory;

@Configuration
public class GrpcConfiguration {
    @Bean
    PaymentServiceGrpc.PaymentServiceBlockingStub stub(GrpcChannelFactory channels){
        return PaymentServiceGrpc.newBlockingStub(channels.createChannel("order"));
    }
}
