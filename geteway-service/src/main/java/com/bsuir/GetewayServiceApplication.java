package com.bsuir;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GetewayServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(GetewayServiceApplication.class, args);


    }
}