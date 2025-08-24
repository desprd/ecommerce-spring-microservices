package com.ilyaproject.gatewayserver.configuration;

import com.ilyaproject.gatewayserver.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayServerConfiguration {

    @Autowired
    private RouteService routeService;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder){
        return builder.routes()
                .route(r -> r.path("/api/rest/course/get")
                        .filters(f->
                                f.circuitBreaker(config ->
                                        config.setName("coursesCircuitBreaker")
                                              .setFallbackUri("forward:/coursesCache")))
                        .uri(routeService.getUriById("catalog")))
                .route(r -> r.path("/api/rest/course/create")
                        .filters(f->
                                f.circuitBreaker(config ->
                                        config.setName("createCoursesCircuitBreaker")
                                                .setFallbackUri("forward:/contactSupport")))
                        .uri(routeService.getUriById("catalog")))
                .route(r -> r.path("/api/rest/author/create")
                        .filters(f->
                                f.circuitBreaker(config ->
                                        config.setName("createAuthorCircuitBreaker")
                                                .setFallbackUri("forward:/contactSupport")))
                        .uri(routeService.getUriById("catalog")))
                .route(r -> r.path("/api/rest/order/create")
                        .filters(f->
                                f.circuitBreaker(config ->
                                        config.setName("createOrderCircuitBreaker")
                                                .setFallbackUri("forward:/contactSupport")))
                        .uri(routeService.getUriById("order")))
                .route(r -> r.path("/api/rest/order/get/{id}")
                        .filters(f->
                                f.circuitBreaker(config ->
                                        config.setName("orderCircuitBreaker")
                                                .setFallbackUri("forward:/contactSupport")))
                        .uri(routeService.getUriById("order")))
                .route(r -> r.path("/api/rest/payment/**")
                        .filters(f->
                                f.circuitBreaker(config ->
                                        config.setName("paymentCircuitBreaker")
                                                .setFallbackUri("forward:/contactSupport")))
                        .uri(routeService.getUriById("payment"))).build();
    }
}
