package com.microservices.orderservice.config;

import org.modelmapper.ModelMapper;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;

@Configuration
public class OrderConfig {
    @Bean
    public WebClient webClient(){
        return WebClient.builder().build();
    }
    @Bean
    @LoadBalanced
    public WebClient.Builder builder(){

//        HttpClient httpClient = HttpClient.create()
//                .responseTimeout(Duration.ofSeconds(3));
        return WebClient.builder();
    }
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
