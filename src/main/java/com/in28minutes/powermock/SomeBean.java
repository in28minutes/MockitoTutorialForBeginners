package com.in28minutes.powermock;

import java.util.List;

public interface SomeBean {
	class Factory {
		public static List<String> create() {
			throw new RuntimeException(
					"I dont want to be executed. I will anyway be mocked out.");
		}
	}
}
