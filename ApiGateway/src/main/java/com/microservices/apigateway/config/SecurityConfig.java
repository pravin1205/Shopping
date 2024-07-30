//package com.microservices.apigateway.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.web.server.SecurityWebFilterChain;
//
//@Configuration
//@EnableWebFluxSecurity
//public class SecurityConfig {
//    public SecurityWebFilterChain webFilterChain(ServerHttpSecurity serverHttpSecurity){
//        serverHttpSecurity
//                .authorizeExchange(authorizeExchangeSpec -> authorizeExchangeSpec
//                        .pathMatchers("/eureka/**")
//                        .permitAll()
//                        .anyExchange()
//                        .authenticated())
//                .oauth2ResourceServer(ServerHttpSecurity.OAuth2ResourceServerSpec::jwt)
//                .csrf(ServerHttpSecurity.CsrfSpec::disable);
//        return serverHttpSecurity.build();
//    }
//}
