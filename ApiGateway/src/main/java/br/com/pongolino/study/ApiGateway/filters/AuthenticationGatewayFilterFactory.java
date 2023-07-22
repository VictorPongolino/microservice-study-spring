package br.com.pongolino.study.ApiGateway.filters;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Objects;

@Component
public class AuthenticationGatewayFilterFactory extends AbstractGatewayFilterFactory<AuthenticationGatewayFilterFactory.Config> {

    public static final String PREFIX_AUTHORIZATION_TOKEN = "Bearer ";
    private final JwtParser JWT_PARSER;

    @Autowired
    public AuthenticationGatewayFilterFactory(Environment environment) {
        final String SECRET_KEY = Objects.requireNonNull(environment.getProperty("jwt.token"));
        this.JWT_PARSER = Jwts.parserBuilder()
                .setSigningKey(new SecretKeySpec(Base64.getEncoder().encode(SECRET_KEY.getBytes()), SignatureAlgorithm.HS512.getJcaName()))
                .build();
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            if(!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) return reject(exchange);

            String authorization = request.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
            try {
                String subject = JWT_PARSER
                        .parseClaimsJws(authorization.replace(PREFIX_AUTHORIZATION_TOKEN, ""))
                        .getBody()
                        .getSubject();

                if (StringUtils.isBlank(subject)) {
                    return reject(exchange);
                }
            }
            catch (Exception e) {
                return reject(exchange);
            }

            return chain.filter(exchange);
        };
    }

    private Mono<Void> reject(ServerWebExchange exchange) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatusCode.valueOf(403));
        return response.setComplete();
    }

    public static class Config {}
}
