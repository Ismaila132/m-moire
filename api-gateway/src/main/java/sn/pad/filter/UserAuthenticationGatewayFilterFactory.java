package sn.pad.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import sn.pad.dao.UserDao;

import java.util.List;

@Component
public class UserAuthenticationGatewayFilterFactory extends AbstractGatewayFilterFactory<UserAuthenticationGatewayFilterFactory.Config> {

    @Value("${jwt.secret}")
    private String secret;

    private final UserDao userDao;

    public UserAuthenticationGatewayFilterFactory(UserDao userDao) {
        super(Config.class);
        this.userDao = userDao;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String token = extractToken(exchange.getRequest().getHeaders());
            if (token != null && validateToken(token)) {
                String username = getUsernameFromToken(token);
                if (username != null) {
                    // Perform any additional logic if needed
                }
            }
            return chain.filter(exchange);
        };
    }

    private String extractToken(HttpHeaders headers) {
        List<String> authHeaders = headers.get(HttpHeaders.AUTHORIZATION);
        if (authHeaders != null && !authHeaders.isEmpty()) {
            String authHeader = authHeaders.get(0);
            if (authHeader.startsWith("Bearer ")) {
                return authHeader.substring(7);
            }
        }
        return null;
    }

    private boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Config {
        private String message;
    }
}
