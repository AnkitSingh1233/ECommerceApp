package in.nareshit.ankit.cntrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import in.nareshit.ankit.test.Calculator;

public class CalculatorTest {
	
	@Test
	public void testAdd() {
		Calculator cal=new Calculator();
		int result=cal.add(10, 20);
		assertEquals(50,result,"Addition Should be Correct");
	}

	

}
