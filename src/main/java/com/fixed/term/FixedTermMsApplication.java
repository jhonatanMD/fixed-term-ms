package com.fixed.term;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
@EnableEurekaClient
@SpringBootApplication
public class FixedTermMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FixedTermMsApplication.class, args);
	}

}
