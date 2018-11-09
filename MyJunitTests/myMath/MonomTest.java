package myMath;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import myMath.Monom;

class MonomTest {
	
	static Monom general, second;

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
		second = null;
	}


	@Test
	void testMonom1() { //check Monom
		second = new Monom(-4, 5);
		int power = 5; 
		double coefficient = -4;
		boolean flag = true;
		if (power != second.get_power() || coefficient != second.get_coefficient()) {
			flag = true;
		}
		assertEquals(true,  flag);
	}
	
	@Test
	void testMonom2() { //check Monom with negative power
		boolean flag;
		try {
			Monom m = new Monom(-4, -5); //this row must throw exception because power is negative
			flag = false; // if flag will change to false so construction don't work right
		}catch(RuntimeException e){
			flag = true;
		}
		assertEquals(true,  flag);
	}

	@Test
	void testMonomString1() { // good input
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
	void testMonomString2() { //power is negative
		String monom = "-3.14*x^-5"; // some wrong string type Monom
		
		boolean flag;
		try {
			general = new Monom(monom); //this row must throw exception because power is negative
			flag = false; // if flag will change to false so construction don't work right 
		}catch(RuntimeException e){
			flag = true;
		}
		assertEquals(true,  flag);
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
		second = new Monom(coefficient, power);

		assertEquals(true, second.equals(general));
	}

	@Test
	void testAdd1() {//if powers of Monoms is equals
		second = new Monom(3, 5) ;
		try {
			general.add(second);
			assertEquals(-1.5, general.get_coefficient());
		}
		catch(RuntimeException e){
			System.err.println("Error: can not add two monoms with different powers");
		}	
	}
	
	@Test
	void testAdd2() {//if powers of Monoms NOT equals!!!
		second = new Monom(3, 6) ;
		boolean flag;
		try {
			general.add(second); //try to add two Monoms with differents powers
			flag = false; //if flag will change to false so add don't work right
		}
		catch(RuntimeException e){
			flag = true;;
		}	
		assertEquals(true, flag);
	}

	@Test
	void testIs_exist1() { //check if general is right Monom, power and coefficient good 
		assertEquals(true, general.is_exist()); // right input
	}
	
	@Test
	void testIs_exist2() { //check if general is right Monom, power and coefficient good 
		general = new Monom(0, 8); //wrong input
		assertEquals(false, general.is_exist());
	}

	@Test
	void testMultiply() {
		second = new Monom(3, 3); // create second Monom
		
		double coefficient = general.get_coefficient() * second.get_coefficient(); //count coefficient
		int power = general.get_power() + second.get_power(); //count power
		
		general.multiply(second); 
		
		boolean flag = true; //check if function work right
		if(general.get_coefficient() != coefficient || general.get_power() != power ) {
			flag = false; // if not equals so change flag to false
		}
		assertEquals(true, flag);
	}

	@Test
	void testEqualsMonom1() { // same Monoms
		second = general.copy();
		assertEquals(true, general.equals(second)); // check answer with true
	}
	
	@Test
	void testEqualsMonom2() { // others Monoms
		second = new Monom(1, 1);;
		assertEquals(false, general.equals(second)); //check answer with false
	}

	@Test
	void testToNegative() {//this function multiply Monom by -1
		double coefficient = general.get_coefficient() * (-1);
		general.toNegative();
		assertEquals(coefficient, general.get_coefficient());
	}

	@Test
	void testCopy() {
		second = general.copy();
		boolean flag = true;
		if(second.get_coefficient() != general.get_coefficient() || second.get_power() != general.get_power()) {
			flag = false;
		}
		assertEquals(true, flag);
	}

}
