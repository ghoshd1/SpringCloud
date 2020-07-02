package com.poc.code.springcloud.netflixeurekanamingserver.netflixeurekanamingserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class NetflixeurekanamingserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(NetflixeurekanamingserverApplication.class, args);
	}

}
