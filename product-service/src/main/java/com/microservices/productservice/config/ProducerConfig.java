package com.microservices.productservice.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProducerConfig {
    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }

}
