package com.ilyaproject.gatewayserver.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "spring.cloud.gateway")
public class GatewayRoutesProperties {

    private List<RouteConfig> routes = new ArrayList<>();

    public List<RouteConfig> getRoutes() {
        return routes;
    }

    public void setRoutes(List<RouteConfig> routes) {
        this.routes = routes;
    }

    public static class RouteConfig {
        private String id;
        private String uri;

        public String getId() { return id; }
        public void setId(String id) { this.id = id; }

        public String getUri() { return uri; }
        public void setUri(String uri) { this.uri = uri; }
    }
}