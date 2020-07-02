package com.poc.code.springcloud.currencyconversionservice.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CurrencyConversionBean {

	private long id;
	private String from;
	private String to;
	private BigDecimal conversionMultiple;
	private BigDecimal quantity;
	private BigDecimal totalCalculationAmount;
	private int port;
	
	
}
