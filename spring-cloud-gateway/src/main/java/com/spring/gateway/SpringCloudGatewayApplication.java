package com.spring.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class SpringCloudGatewayApplication {

    @Value("${test}")
    private String fromConfigMsg;

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudGatewayApplication.class, args);
    }

    @Bean
    public String fromConfigGreetingStringBean() {
        log.info("★★★★ From Config Message {} ★★★★", fromConfigMsg);
        return "Hi";
    }

}
