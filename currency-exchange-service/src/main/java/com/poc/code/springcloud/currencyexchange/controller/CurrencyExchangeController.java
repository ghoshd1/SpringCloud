package com.poc.code.springcloud.currencyexchange.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.poc.code.springcloud.currencyexchange.dao.ExchangeValueRepository;
import com.poc.code.springcloud.currencyexchange.entity.ExchangeValue;
import com.poc.code.springcloud.currencyexchange.exception.DataNotFoundException;

@RestController
public class CurrencyExchangeController {
	
	Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);
	@Autowired
	Environment environment;
	
	@Autowired
	ExchangeValueRepository exchangeValueRepository;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retreiveExchangeValue(@PathVariable String from,@PathVariable String to) {	
		Optional<ExchangeValue> optExchangeValue = exchangeValueRepository.findByFromAndTo(from,to);
		if(optExchangeValue.isPresent()) {
			optExchangeValue.get().setPort(Integer.parseInt(environment.getProperty("local.server.port")));
			logger.info("{}",optExchangeValue.get());
			return optExchangeValue.get();
		}
		
		throw new DataNotFoundException(String.format("Data for %s to %s is not found",from,to));	
	}

}
