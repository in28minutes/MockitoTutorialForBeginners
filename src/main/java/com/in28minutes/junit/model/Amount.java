package com.in28minutes.junit.model;

import java.math.BigDecimal;

public interface Amount {
	BigDecimal getValue();

	Currency getCurrency();
}
