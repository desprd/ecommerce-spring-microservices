package com.ilyaproject.gatewayserver.service;

import com.ilyaproject.gatewayserver.configuration.GatewayRoutesProperties;
import org.springframework.stereotype.Service;

@Service
public class RouteService {
    private final GatewayRoutesProperties properties;

    public RouteService(GatewayRoutesProperties properties) {
        this.properties = properties;
    }

    public String getUriById(String id) {
        return properties.getRoutes().stream()
                .filter(r -> r.getId().equals(id))
                .map(GatewayRoutesProperties.RouteConfig::getUri)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Route not found: " + id));
    }
}
