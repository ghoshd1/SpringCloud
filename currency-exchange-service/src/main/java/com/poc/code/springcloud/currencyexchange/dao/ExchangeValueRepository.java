package com.poc.code.springcloud.currencyexchange.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poc.code.springcloud.currencyexchange.entity.ExchangeValue;

public interface ExchangeValueRepository extends JpaRepository<ExchangeValue, Integer>{

	Optional<ExchangeValue> findByFromAndTo(String from, String to);

}
