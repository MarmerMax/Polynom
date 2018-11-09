package myMath;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import myMath.Monom;

class MonomTest {
	
	static Monom general;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		general = new Monom(-4.5, 5);
		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		general = new Monom(-4.5, 5);
	}

	@AfterEach
	void tearDown() throws Exception {
	}


	@Test
	void testMonom1() { //check Monom
		Monom m = new Monom(-4, 5);
		int power = 5; 
		double coefficient = -4;
		boolean flag = true;
		if (power != m.get_power() || coefficient != m.get_coefficient()) {
			flag = true;
		}
		assertEquals(true,  flag);
	}
	
	@Test
	void testMonom2() { //check Monom with negative power
		boolean flag;
		try {
			Monom m = new Monom(-4, -5);
			flag = false;
		}catch(RuntimeException e){
			System.err.println("Error: power is negative!!!");
			flag = true;
		}
		assertEquals(true,  flag);
	}

	@Test
	void testMonomString() {
		String monom = "-3.14*x^5";
		general = new Monom(monom);
		int power = 5;
		double coefficient = -3.14;
		boolean flag = true;
		if (power != general.get_power() || coefficient != general.get_coefficient()) {
			flag = false;
		}
		assertEquals(true, flag);
	}

	@Test
	void testGet_coefficient() {
		double coefficient = -4.5;
		assertEquals(coefficient, general.get_coefficient());
	}

	@Test
	void testGet_power() {
		int power = 5;
		assertEquals(power, general.get_power());
	}

	@Test
	void testF() {
		int x = 4;
		double res = -4.5 * Math.pow(4, 5);
		assertEquals(res, general.f(x));
	}

	@Test
	void testToString() {
		String str = "-4.5*x^5";
		assertEquals(str, general.toString());
	}

	@Test
	void testDerivative() {
		general = general.derivative();
		
		int power = 5;
		double coefficient = -4.5;
		coefficient *= power;
		power--;
		Monom temp = new Monom(coefficient, power);

		assertEquals(true, temp.equals(general));
	}

	@Test
	void testAdd() {
		Monom temp = new Monom(3, 5) ;
		try {
			general.add(temp);
			assertEquals(-1.5, general.get_coefficient());
		}
		catch(RuntimeException e){
			System.err.println("Error: can not add two monoms with different powers");
		}	
	}

	@Test
	void testIs_exist() {
		assertEquals(true, general.is_exist());
	}
//
//	@Test
//	void testMultiply() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testEqualsMonom() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testToNegative() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testCopy() {
//		fail("Not yet implemented");
//	}

}
