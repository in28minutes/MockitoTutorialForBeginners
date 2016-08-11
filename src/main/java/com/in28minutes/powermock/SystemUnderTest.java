package com.in28minutes.powermock;

import java.util.ArrayList;
import java.util.List;

public class SystemUnderTest {
	private Dependency dependency;

	public int methodUsingAnArrayListConstructor() {
		ArrayList list = new ArrayList();
		return list.size();
	}

	public int methodInvokingAFactoryMethodCall() {
		List list = SomeBean.Factory.create();
		return list.size();
	}

	public int methodUnderTest() {
		//privateMethodUnderTest calls static method SomeClass.staticMethod
		return privateMethodUnderTest();
	}

	public long normalMethodCallingADependenyMethod() {
		List<Integer> stats = dependency.retrieveAllStats();
		long sum = 0;
		for (int stat : stats)
			sum += stat;
		return sum;
	}

	private int privateMethodUnderTest() {
		List<Integer> stats = dependency.retrieveAllStats();
		long sum = 0;
		for (int stat : stats)
			sum += stat;
		return SomeClass.staticMethod(sum);
	}
	//Powermock 1.6.4
	//Mockito 1.10.19
	// JUnit 4.4
}
