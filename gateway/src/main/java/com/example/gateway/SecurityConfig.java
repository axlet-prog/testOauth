package com.example.gateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

/**
 * <pre>
 * <div><strong>Project name:</strong> DemoOAuth2 </div>
 * <div><strong>Creation date:</strong> 2025-09-23 </div>
 * </pre>
 *
 * @author Ivannikov Alexey
 * @since 1.0.0
 */
@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {


    @Bean
    public SecurityWebFilterChain securityWebFilterChain(
        ServerHttpSecurity http
    ) {
        http
            .authorizeExchange(authorize -> authorize
                .pathMatchers("/login/oauth2/**").permitAll()
                .anyExchange().authenticated()
            )
            .oauth2Login(Customizer.withDefaults())
            .csrf(ServerHttpSecurity.CsrfSpec::disable)
            .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
            .formLogin(ServerHttpSecurity.FormLoginSpec::disable);

        return http.build();
    }

    // @Bean
    // public ClientRegistrationRepository clientRegistrationRepository() {
    //     return new InMemoryClientRegistrationRepository(this.githubClientRegistration());
    // }
    //
    // private ClientRegistration githubClientRegistration() {
    //     return ClientRegistration.withRegistrationId("github")
    //         .clientId("Ov23ligV0vfMlsEmQNyi")
    //         .clientSecret("d2e51ae2a6c41b081b5cb7bbf0b1790f857123ed")
    //         .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
    //         .authorizationUri("https://github.com/login/oauth/authorize")
    //         .redirectUri("{baseUrl}/login/oauth2/code/{registrationId}")
    //         // URL, на который ваше приложение обменяет code на access_token
    //         .tokenUri("https://github.com/login/oauth/access_token")
    //         .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
    //         // URL для получения информации о пользователе после получения токена
    //         .userInfoUri("https://api.github.com/user")
    //         .userNameAttributeName("login")
    //         .scope("user")
    //         .build();
    // }

    // private ClientRegistration googleClientRegistration() {
    //     return ClientRegistration.withRegistrationId("github")
    //         .clientId("Ov23ligV0vfMlsEmQNyi")
    //         .clientSecret("d2e51ae2a6c41b081b5cb7bbf0b1790f857123ed")
    //         .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
    //         .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
    //         .redirectUri("{baseUrl}/login/oauth2/code/{registrationId}")
    //         .scope("openid", "profile", "email", "address", "phone")
    //         .authorizationUri("https://accounts.google.com/o/oauth2/v2/auth")
    //         .tokenUri("https://www.googleapis.com/oauth2/v4/token")
    //         .userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo")
    //         .userNameAttributeName(IdTokenClaimNames.SUB)
    //         .jwkSetUri("https://www.googleapis.com/oauth2/v3/certs")
    //         .clientName("Google")
    //         .build();
    // }

}