package com.poc.code.springcloud.limitsservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties("limits-service")
@Data
public class LimitServiceConfig {
	private int maximum;
	private int minimum;
	
	public LimitServiceConfig(int max, int min) {
		this.maximum=max;
		this.minimum=min;
	}
	public LimitServiceConfig() {
		
	}
}
