# Steps for Upgrading to Junit5

## Step1: Replace the following with Junit5 and `mockito-all` with `mockito-inline`

**AS-IS**

```xml
<dependencies>
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.12</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.mockito</groupId>
        <artifactId>mockito-all</artifactId>
        <version>1.10.19</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```
**TO-BE**

```xml
<dependencies>
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter</artifactId>
        <version>5.10.2</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter-api</artifactId>
        <version>5.10.2</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.junit.platform</groupId>
        <artifactId>junit-platform-suite-engine</artifactId>
        <version>1.10.2</version>
    </dependency>
    <dependency>
        <groupId>org.mockito</groupId>
        <artifactId>mockito-inline</artifactId>
        <version>3.6.0</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.hamcrest</groupId>
        <artifactId>hamcrest</artifactId>
        <version>2.2</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.mockito</groupId>
        <artifactId>mockito-core</artifactId>
        <version>5.4.0</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.mockito</groupId>
        <artifactId>mockito-junit-jupiter</artifactId>
        <version>4.5.1</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```
## Step2: Upgrade Java Compiler Version with the latest one under build section

**AS-IS**
```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <configuration>
                <source>1.8</source>
                <target>1.8</target>
            </configuration>
        </plugin>
    </plugins>
</build>
```
> `<source>` and `<target>` has been replaced with `<release></release>` tag

**TO-BE**
```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <configuration>
                <release>21</release> 
            </configuration>
        </plugin>
    </plugins>
</build>
```

## Step3: Changes in `ArraysCompareTest` class
Qualified PackageName has been changed

**AS-IS**

```java
import static org.junit.Assert.*;

import org.junit.Test;
```

**TO-BE**
```java
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
```

### MethodName : testArraySort_NullArray

**AS-IS**

```java
@Test(expected=NullPointerException.class)
public void testArraySort_NullArray() {
    int[] numbers = null;
    Arrays.sort(numbers);
}
```
###### Refactored using `assertThrows` and functional approach
**TO-BE**
```java
@Test
public void testArraySort_NullArray() {
    assertThrows(NullPointerException.class, () -> {
        int[] numbers = null;
        Arrays.sort(numbers);
    });
}
```

### MethodName : testSort_Performance

**AS-IS**

```java
@Test(timeout=100)
```
**TO-BE**
##### Added using `@TimeOut` annotation
```java
@Test
@Timeout(value = 100)
```

## Step4: Changes in `ArraysTest` class
Qualified PackageName has been changed

**AS-IS**

```java
import org.junit.Test;
```
**TO-BE**

```java
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
```
### MethodName : testPerformance

**AS-IS**

```java
@Test(timeout=100)
```
**TO-BE**
##### Added using `@TimeOut` annotation
```java
@Test
@Timeout(value = 100)
```

## Step4: Changes in `ClientBOTest` class
Qualified PackageName has been changed

**AS-IS**
```java
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
```
**TO-BE**

```java
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
```

### MethodName : testClientProductSum1

**AS-IS**

```java
@Test(expected = DifferentCurrenciesException.class)
public void testClientProductSum1() throws DifferentCurrenciesException {

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
}
```
**TO-BE**
###### Refactored using `assertThrows` and functional approach

```java
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
```

## Step5: Changes in `ClientBOTestRefactored` class
Qualified PackageName has been changed

**AS-IS**
```java
import static org.junit.Assert.assertEquals;
```
**TO-BE**
```java
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows; // Newly Added

import org.junit.jupiter.api.Test;
```

### MethodName : `testClientProductSum_DifferentCurrencies_ThrowsException`

**AS-IS**

```java
@Test(expected = DifferentCurrenciesException.class)
public void testClientProductSum_DifferentCurrencies_ThrowsException()
        throws DifferentCurrenciesException {

    Amount[] amounts = {
            new AmountImpl(new BigDecimal("5.0"), Currency.EURO),
            new AmountImpl(new BigDecimal("6.0"), Currency.INDIAN_RUPEE) };

    List<Product> products = createProductListWithAmounts(amounts);

    @SuppressWarnings("unused")
    Amount actual = clientBO.getClientProductsSum(products);

}
```
**TO-BE**
###### Refactored using `assertThrows` and functional approach

```java
@Test
public void testClientProductSum_DifferentCurrencies_ThrowsException()
        throws DifferentCurrenciesException {
    assertThrows(DifferentCurrenciesException.class, () -> {

        Amount[] amounts = {
                new AmountImpl(new BigDecimal("5.0"), Currency.EURO),
                new AmountImpl(new BigDecimal("6.0"), Currency.INDIAN_RUPEE)};

        List<Product> products = createProductListWithAmounts(amounts);

        @SuppressWarnings("unused")
        Amount actual = clientBO.getClientProductsSum(products);

    });

}
```

## Step6: Changes in `DummyTestSuite` class
Qualified PackageName has been changed

**AS-IS**

```java
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
```
**TO-BE**

```java
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
```

**AS-IS**

```java
@RunWith(Suite.class)
@SuiteClasses({ArraysTest.class,StringHelperTest.class})
```
**TO-BE**

```java
@Suite
@SelectClasses({ArraysTest.class,StringHelperTest.class})
```

## Step6: Changes in `FirstMockitoTest` class
Qualified PackageName has been changed

**AS-IS**

```java
import static org.junit.Assert.assertTrue;

import org.junit.Test;
```
**TO-BE**

```java
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
```

## Step7: Changes in `HamcrestMatcherTest` class
Qualified PackageName has been changed

**AS-IS**

```java
import org.junit.Test;
```
**TO-BE**

```java
import org.junit.jupiter.api.Test;
```

## Step8: Changes in `ListTest` class
Qualified PackageName has been changed

**AS-IS**

```java
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;
```
**TO-BE**

```java
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
```

### MethodName : `letsMockListGetToThrowException`

**AS-IS**

```java
@Test(expected = RuntimeException.class)
public void letsMockListGetToThrowException() {
    List<String> list = mock(List.class);
    when(list.get(Mockito.anyInt())).thenThrow(
            new RuntimeException("Something went wrong"));
    list.get(0);
}
```
**TO-BE**
###### Refactored using `assertThrows` and functional approach

```java
@Test
public void letsMockListGetToThrowException() {
    assertThrows(RuntimeException.class, () -> {
        List<String> list = mock(List.class);
        when(list.get(Mockito.anyInt())).thenThrow(
                new RuntimeException("Something went wrong"));
        list.get(0);
    });
}
```

## Step9: Changes in `PowerMockitoMockingStaticMethodTest` class
#### Class Name `PowerMockitoMockingStaticMethodTest` has been changed to `MockitoMockingStaticMethodTest`

Qualified PackageName has been changed

**AS-IS**

```java
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
```
**TO-BE**

```java
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
```

**AS-IS**
```java
@RunWith(PowerMockRunner.class)
@PrepareForTest({ UtilityClass.class /*The class with static method to be mocked*/})
```
**TO-BE**

```java
@ExtendWith(MockitoExtension.class)
```
### MethodName : `powerMockito_MockingAStaticMethodCall`

**AS-IS**

```java
@Test
public void powerMockito_MockingAStaticMethodCall() {

    when(dependencyMock.retrieveAllStats()).thenReturn(
            Arrays.asList(1, 2, 3));

    PowerMockito.mockStatic(UtilityClass.class);

    when(UtilityClass.staticMethod(anyLong())).thenReturn(150);

    assertEquals(150, systemUnderTest.methodCallingAStaticMethod());

    //To verify a specific method call
    //First : Call PowerMockito.verifyStatic() 
    //Second : Call the method to be verified
    PowerMockito.verifyStatic();
    UtilityClass.staticMethod(1 + 2 + 3);

    // verify exact number of calls
    //PowerMockito.verifyStatic(Mockito.times(1));

}
```
**TO-BE**
###### Refactored using `mockStatic` and functional approach

```java
@Test
public void powerMockito_MockingAStaticMethodCall() {
    when(dependencyMock.retrieveAllStats()).thenReturn(Arrays.asList(1, 2, 3));

    MockedStatic<UtilityClass> mockedStatic = mockStatic(UtilityClass.class);

    mockedStatic.when(() -> UtilityClass.staticMethod(anyLong())).thenReturn(150);

    assertEquals(150, systemUnderTest.methodCallingAStaticMethod());

    mockedStatic.verify(() -> UtilityClass.staticMethod(1 + 2 + 3), times(1));
}
```
## Step10: Changes in `PowerMockitoTestingPrivateMethodTest` class
Qualified PackageName has been changed

**AS-IS**

```java
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;
```
**TO-BE**

```java
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Method; // Added

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;
```

**AS-IS**
```java
@RunWith(PowerMockRunner.class)
```
**TO-BE**

```java
@ExtendWith(MockitoExtension.class)
```

### MethodName : `powerMockito_MockingAStaticMethodCall`

**AS-IS**
```java
when(dependencyMock.retrieveAllStats()).thenReturn(
				Arrays.asList(1, 2, 3));
long value = (Long) Whitebox.invokeMethod(systemUnderTest,
				"privateMethodUnderTest");
assertEquals(6, value);
```
**TO-BE**

```java
Method method = systemUnderTest.getClass().getDeclaredMethod("privateMethodUnderTest");
method.setAccessible(true);
when(dependencyMock.retrieveAllStats()).thenReturn(Arrays.asList(1, 2, 3));
long value = (Long) method.invoke(systemUnderTest);
assertEquals(6, value);
```
## Step11: Changes in `QuickBeforeAfterTest` class
Qualified PackageName has been changed

**AS-IS**

```java
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
```
**TO-BE**

```java
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
```

### Annotations used in this class has been changed from 

* @BeforeClass > @BeforeAll
* @Before > @BeforeEach
* @After > @AfterEach
* @AfterClass > @AfterAll

## Step12: Changes in `SpyTest` class
Qualified PackageName has been changed

**AS-IS**

```java
import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.stub; // Removed

import org.junit.Test;

```
**TO-BE**

```java
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
```

### Removed method name `creatingASpyOnArrayList_overridingSpecificMethods`


## Step13: Changes in `StringHelperParameterizedTest` class
Qualified PackageName has been changed

**AS-IS**

```java
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

```
**TO-BE**

```java
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
```

2. Removed `@RunWith(Parameterized.class)` in the class level
3. Change in the constructor

**AS-IS**
```java
public StringHelperParameterizedTest(String input, String expectedOutput) {
		this.input = input;
		this.expectedOutput = expectedOutput;
}
```
**TO-BE**

```java
public void initStringHelperParameterizedTest(String input, String expectedOutput) {
		this.input = input;
		this.expectedOutput = expectedOutput;
}
```
4. Remove `@Parameters` in `testConditions` method 
5. Refactor `testTruncateAInFirst2Positions` method

**AS-IS**

```java
@Test
public void testTruncateAInFirst2Positions() {
    assertEquals(expectedOutput, 
            helper.truncateAInFirst2Positions(input));
}
```
**TO_BE**

```java
@MethodSource("testConditions")
@ParameterizedTest
public void truncateAInFirst2Positions(String input, String expectedOutput) {
    initStringHelperParameterizedTest(input, expectedOutput);
    assertEquals(expectedOutput, helper.truncateAInFirst2Positions(input));
}
```

## Step14: Changes in `StringHelperTest` class
Qualified PackageName has been changed

**AS-IS**

```java
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

```
**TO-BE**

```java
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
```

### Annotations used in this class has been changed from

* @Before > @BeforeEach

## Step15: Changes in `TodoBusinessImplMockitoInjectMocksTest` class
Qualified PackageName has been changed

**AS-IS**

```java
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.runners.MockitoJUnitRunner;
```
**TO-BE**

```java
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;
```
**AS-IS**
```java
@RunWith(PowerMockRunner.class)
```
**TO-BE**

```java
@ExtendWith(MockitoExtension.class)
```

## Step16: Changes in `TodoBusinessImplMockitoRulesTest` class
Qualified PackageName has been changed

**AS-IS**

```java
import org.junit.Rule; // Removed
import org.junit.Test;

import org.mockito.junit.MockitoJUnit; // Removed 
import org.mockito.junit.MockitoRule; // Removed
```
**TO-BE**

```java
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;
```

**AS-IS**
```java
@RunWith(PowerMockRunner.class)
```
**TO-BE**

```java
@ExtendWith(MockitoExtension.class)
```

### Removed this
```java
@Rule
public MockitoRule mockitoRule = MockitoJUnit.rule();
```

## Step17: Changes in `TodoBusinessImplMockitoTest` class
Qualified PackageName has been changed

**AS-IS**

```java
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.Test;
```
**TO-BE**
```java
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
```

## Step18: Changes in `TodoBusinessImplStubTest` class
Qualified PackageName has been changed

**AS-IS**
```java
import static org.junit.Assert.assertEquals;

import org.junit.Test;
```
**TO-BE**
```java
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
```

### Happy Learning @in28minutes