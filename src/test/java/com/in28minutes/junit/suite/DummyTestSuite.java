package com.in28minutes.junit.suite;

import com.in28minutes.junit.helper.ArraysTest;
import com.in28minutes.junit.helper.StringHelperTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ArraysTest.class,StringHelperTest.class})
public class DummyTestSuite {

}
