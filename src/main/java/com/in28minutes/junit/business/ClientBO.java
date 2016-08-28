package com.in28minutes.junit.business;

import java.util.List;

import com.in28minutes.junit.business.exception.DifferentCurrenciesException;
import com.in28minutes.junit.model.Amount;
import com.in28minutes.junit.model.Product;

public interface ClientBO {

	Amount getClientProductsSum(List<Product> products)
			throws DifferentCurrenciesException;

}