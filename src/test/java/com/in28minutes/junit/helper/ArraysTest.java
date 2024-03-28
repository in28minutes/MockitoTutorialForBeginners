package com.in28minutes.junit.helper;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

public class ArraysTest {
	@Test
	@Timeout(value = 100)
	public void performance() {
		for(int  i=0;i<1000000;i++){
			Arrays.sort(new int[]{i,i-1,i+1});
		}
	}
}
