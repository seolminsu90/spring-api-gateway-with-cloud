package com.spring.cloud.eureka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
@EnableEurekaServer
public class SpringCloudEurekaApplication {

	@Value("${test}")
	private String fromConfigMsg;

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudEurekaApplication.class, args);
	}

	@Bean
	public String fromConfigGreetingStringBean() {
		log.info("★★★★ From Config Message {} ★★★★", fromConfigMsg);
		return "Hi";
	}

}
