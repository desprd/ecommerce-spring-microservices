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
                .route(r -> r.path("/api/rest/courses/**")
                        .filters(f->
                                f.circuitBreaker(config ->
                                        config.setName("coursesCircuitBreaker")
                                              .setFallbackUri("forward:/contactsupport")))
                        .uri(routeService.getUriById("catalog"))).build();
    }
}
