package com.clarity.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.in28minutes.junit.business.ClientBO;

import org.junit.jupiter.api.Test;
import com.in28minutes.junit.business.ClientBOImpl;
import com.in28minutes.junit.business.exception.DifferentCurrenciesException;
import com.in28minutes.junit.model.Amount;
import com.in28minutes.junit.model.AmountImpl;
import com.in28minutes.junit.model.Currency;
import com.in28minutes.junit.model.Product;
import com.in28minutes.junit.model.ProductImpl;
import com.in28minutes.junit.model.ProductType;

public class ClientBOTest {

	private ClientBO clientBO = new ClientBOImpl();

	@Test
	public void testClientProductSum() throws DifferentCurrenciesException {

		List<Product> products = new ArrayList<Product>();

		products.add(new ProductImpl(100, "Product 15",
				ProductType.BANK_GUARANTEE, new AmountImpl(
						new BigDecimal("5.0"), Currency.EURO)));

		products.add(new ProductImpl(120, "Product 20",
				ProductType.BANK_GUARANTEE, new AmountImpl(
						new BigDecimal("6.0"), Currency.EURO)));

		Amount temp = clientBO.getClientProductsSum(products);

		assertEquals(Currency.EURO, temp.getCurrency());
		assertEquals(new BigDecimal("11.0"), temp.getValue());
	}

	@Test
	public void testClientProductSum1() throws DifferentCurrenciesException {
		assertThrows(DifferentCurrenciesException.class, () -> {

			List<Product> products = new ArrayList<Product>();

			products.add(new ProductImpl(100, "Product 15",
					ProductType.BANK_GUARANTEE, new AmountImpl(
							new BigDecimal("5.0"), Currency.INDIAN_RUPEE)));

			products.add(new ProductImpl(120, "Product 20",
					ProductType.BANK_GUARANTEE, new AmountImpl(
							new BigDecimal("6.0"), Currency.EURO)));

			@SuppressWarnings("unused")
			Amount temp = null;

			temp = clientBO.getClientProductsSum(products);
		});
	}

	@Test
	public void testClientProductSum2() {

		List<Product> products = new ArrayList<Product>();

		Amount temp = null;

		try {
			temp = clientBO.getClientProductsSum(products);
		} catch (DifferentCurrenciesException e) {
		}
		assertEquals(Currency.EURO, temp.getCurrency());
		assertEquals(BigDecimal.ZERO, temp.getValue());
	}

}