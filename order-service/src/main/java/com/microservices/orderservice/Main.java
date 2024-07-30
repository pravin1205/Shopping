package com.microservices.orderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
//import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableFeignClients
//@EnableHystrix
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class,args);
    }
}