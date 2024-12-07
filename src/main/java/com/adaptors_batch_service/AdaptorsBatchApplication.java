package com.adaptors_batch_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AdaptorsBatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdaptorsBatchApplication.class, args);
	}

}
