package com.example.gateway;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerWebExchange;

/**
 * <pre>
 * <div><strong>Project name:</strong> DemoOAuth2 </div>
 * <div><strong>Creation date:</strong> 2025-09-21 </div>
 * </pre>
 *
 * @author Ivannikov Alexey
 * @since 1.0.0
 */
@Component
@RestControllerAdvice
public class TokenCheckGatewayFilter extends AbstractGatewayFilterFactory<TokenCheckGatewayFilter.Config> {

    public TokenCheckGatewayFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            System.out.println("TokenCheckGatewayFilter");

            return ReactiveSecurityContextHolder.getContext()
                .flatMap(securityContext -> {
                    var authentication = securityContext.getAuthentication();
                    if (authentication != null && authentication.getPrincipal() instanceof DefaultOAuth2User) {
                        var user = (DefaultOAuth2User) authentication.getPrincipal();
                        String fullName = (String) user.getAttributes().get("name");
                        if (fullName == null) {
                            fullName = (String) user.getAttributes().get("login");
                        }
                        ServerHttpRequest modifiedRequest = exchange.getRequest().mutate()
                            .header("X-name", fullName)
                            .build();
                        ServerWebExchange modifiedExchange = exchange.mutate().request(modifiedRequest).build();
                        return chain.filter(modifiedExchange);
                    }
                    return chain.filter(exchange);
                });
        };
    }

    public static class Config {
    }
}
