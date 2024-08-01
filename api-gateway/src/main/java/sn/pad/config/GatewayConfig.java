package sn.pad.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import sn.pad.filter.UserAuthenticationGatewayFilterFactory;

@Configuration
public class GatewayConfig {

    @Autowired
    private UserAuthenticationGatewayFilterFactory userAuthenticationGatewayFilterFactory;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        GatewayFilter filter = userAuthenticationGatewayFilterFactory.apply(new UserAuthenticationGatewayFilterFactory.Config());
        return builder.routes()
                .route("BORDEREAU-SERVICE", r -> r.path("/bordereauapi/**").filters(f -> f.filter(filter)).uri("lb://BORDEREAU-SERVICE"))
                .route("JOURNALIER-SERVICE", r -> r.path("/journalierapi/**").filters(f -> f.filter(filter)).uri("lb://JOURNALIER-SERVICE"))
                .route("NAVIRE-SERVICE", r -> r.path("/navireapi/**").filters(f -> f.filter(filter)).uri("lb://NAVIRE-SERVICE"))
                .route("PORT-SERVICE", r -> r.path("/portapi/**").filters(f -> f.filter(filter)).uri("lb://PORT-SERVICE"))
                .route("CATEGORIE-SERVICE", r -> r.path("/categorieapi/**").filters(f -> f.filter(filter)).uri("lb://CATEGORIE-SERVICE"))
                .build();
    }
}
