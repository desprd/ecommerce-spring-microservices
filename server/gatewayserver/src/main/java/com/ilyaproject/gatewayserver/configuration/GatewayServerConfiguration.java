package com.ilyaproject.gatewayserver.configuration;

import com.ilyaproject.gatewayserver.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import reactor.core.publisher.Mono;

import java.time.Duration;

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
                                              .setFallbackUri("forward:/coursesCache"))
                                .retry(retryConfig -> retryConfig.setRetries(3)
                                        .setMethods(HttpMethod.GET)
                                        .setBackoff(Duration.ofMillis(100), Duration.ofMillis(1000), 2, true))
                                        .requestRateLimiter(config -> config.setRateLimiter(redisRateLimiter())
                                                .setKeyResolver(userKeyResolver())))
                        .uri(routeService.getUriById("catalog")))
                .route(r -> r.path("/api/rest/course/create")
                        .filters(f->
                                f.circuitBreaker(config ->
                                        config.setName("createCoursesCircuitBreaker")
                                                .setFallbackUri("forward:/contactSupport"))
                                        .requestRateLimiter(config -> config.setRateLimiter(redisRateLimiter())
                                                .setKeyResolver(userKeyResolver())))
                        .uri(routeService.getUriById("catalog")))
                .route(r -> r.path("/api/rest/author/create")
                        .filters(f->
                                f.circuitBreaker(config ->
                                        config.setName("createAuthorCircuitBreaker")
                                                .setFallbackUri("forward:/contactSupport"))
                                        .requestRateLimiter(config -> config.setRateLimiter(redisRateLimiter())
                                                .setKeyResolver(userKeyResolver())))
                        .uri(routeService.getUriById("catalog")))
                .route(r -> r.path("/api/rest/order/create")
                        .filters(f->
                                f.circuitBreaker(config ->
                                        config.setName("createOrderCircuitBreaker")
                                                .setFallbackUri("forward:/contactSupport"))
                                        .requestRateLimiter(config -> config.setRateLimiter(redisRateLimiter())
                                                .setKeyResolver(userKeyResolver())))
                        .uri(routeService.getUriById("order")))
                .route(r -> r.path("/api/rest/order/get/{id}")
                        .filters(f->
                                f.circuitBreaker(config ->
                                        config.setName("orderCircuitBreaker")
                                                .setFallbackUri("forward:/contactSupport"))
                                .retry(retryConfig -> retryConfig.setRetries(3)
                                        .setMethods(HttpMethod.GET)
                                        .setBackoff(Duration.ofMillis(100), Duration.ofMillis(1000), 2, true))
                                        .requestRateLimiter(config -> config.setRateLimiter(redisRateLimiter())
                                                .setKeyResolver(userKeyResolver())))
                        .uri(routeService.getUriById("order")))
                .route(r -> r.path("/api/rest/payment/**")
                        .filters(f->
                                f.circuitBreaker(config ->
                                        config.setName("paymentCircuitBreaker")
                                                .setFallbackUri("forward:/contactSupport"))
                                        .requestRateLimiter(config -> config.setRateLimiter(redisRateLimiter())
                                                .setKeyResolver(userKeyResolver())))
                        .uri(routeService.getUriById("payment"))).build();
    }

    @Bean
    public RedisRateLimiter redisRateLimiter(){
        return new RedisRateLimiter(1, 1, 1);
    }

    @Bean
    public KeyResolver userKeyResolver(){
        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getHostString());
    }
}
