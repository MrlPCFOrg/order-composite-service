package com.oms.order;

import com.oms.common.security.config.EnableOMSSecurity;
import com.oms.common.web.EnableOMSCommonWeb;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableOMSCommonWeb
//@EnableOMSSecurity
//@EnableEurekaClient
//@EnableCircuitBreaker
public class OrderCompositeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderCompositeServiceApplication.class, args);
	}
}
