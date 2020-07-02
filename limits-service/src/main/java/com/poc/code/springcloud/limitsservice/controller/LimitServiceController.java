package com.poc.code.springcloud.limitsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.poc.code.springcloud.limitsservice.config.LimitServiceConfig;

@RestController
public class LimitServiceController {
	
	@Autowired
	LimitServiceConfig limitConfig;
	

	@GetMapping("/limit-service/config")
	public LimitServiceConfig getConfig() {
		return new LimitServiceConfig(limitConfig.getMaximum(),limitConfig.getMinimum());
	}
	
	
	@GetMapping("/limit-service/fault-tolerence-example")
	@HystrixCommand(fallbackMethod = "getdefaultConfig")
	public LimitServiceConfig getFaultConfig() {
		throw new RuntimeException("Not Available");
	}
	
	
	public LimitServiceConfig getdefaultConfig() {
		return new LimitServiceConfig(10000,0);
	}
}
