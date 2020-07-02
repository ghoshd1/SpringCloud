package com.poc.code.springcloud.currencyconversionservice.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.poc.code.springcloud.currencyconversionservice.dto.CurrencyConversionBean;
import com.poc.code.springcloud.currencyconversionservice.proxy.CurrencyExchangeServiceProxy;

@RestController
public class CurrencyConversionController {

	Logger logger = LoggerFactory.getLogger(CurrencyConversionController.class);
	
	@Autowired
	CurrencyExchangeServiceProxy proxy;
	
	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrency(@PathVariable String from,@PathVariable String to,@PathVariable BigDecimal quantity) {
	
		//Approach 1:: Using Rest Template
		Map<String,String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to",to);
		
		ResponseEntity<CurrencyConversionBean> responseEntity = new RestTemplate().getForEntity("http://localhost:8001/currency-exchange/from/{from}/to/{to}", 
				CurrencyConversionBean.class, uriVariables);
		CurrencyConversionBean currencyConversionBean = responseEntity.getBody();
		currencyConversionBean.setQuantity(quantity);
		currencyConversionBean.setTotalCalculationAmount(quantity.multiply(currencyConversionBean.getConversionMultiple()));
		return currencyConversionBean;
		
		
	}
	
	@GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrencyFeign(@PathVariable String from,@PathVariable String to,@PathVariable BigDecimal quantity) {	
		//Approach 2:: Using Proxy
		CurrencyConversionBean currencyConversionBean = proxy.retreiveExchangeValue(from, to);
		currencyConversionBean.setQuantity(quantity);
		currencyConversionBean.setTotalCalculationAmount(quantity.multiply(currencyConversionBean.getConversionMultiple()));
		
		logger.info("{}",currencyConversionBean);
		
		return currencyConversionBean;
		
		
	}
}
