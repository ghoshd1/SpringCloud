package com.poc.code.springcloud.currencyexchange.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.Data;

@Data
@Entity
public class ExchangeValue {

	@Id
	private int id;
	@Column(name = "CURRENCY_FROM")
	private String from;
	@Column(name="CURRENCY_TO")
	private String to;
	private BigDecimal conversionMultiple;
	@Transient
	private int port;
	
}
