package myMath;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Iterator;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.omg.PortableServer.POA;

class PolynomTest {
	
	static Polynom general, second;
	static Monom m1, m2, m3, m4, m5, m6;
	static double x; //x for function f(x)

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		general = new Polynom("-5*x^4+4*x^3+3*x^2");
		m1 = new Monom(-5, 4);
		m2 = new Monom(4, 3);
		m3 = new Monom(3, 2);
		
	}

	@AfterEach
	void tearDown() throws Exception {
		second = null;
		m4 = m5 = m6 = null;
		x = 0;
	}

	@Test
	void testPolynom1() { //Empty construction
		second = new Polynom(); //create polynom
		if(second == null) { //check if this really created
			assertTrue(false);
		} else {
			assertTrue(true);
		}
	}

	@Test
	void testPolynom2() { //Copy construction with correct input
		second = new Polynom(general);
		assertTrue(second.equals(general));
	}
	
	@Test
	void testPolynom3() {//Copy construction with INCORRECT input!!! (null)
		boolean flag;
		Polynom empty = new Polynom(); //create polynom and do it null after
		empty = null;
		try {
			second = new Polynom(empty); //incorrect input to construction throw exception
			flag = false; //if there was no error before so flag change to false
		}catch(RuntimeException e){
			flag = true; //we got the exception
		}
		assertTrue(flag);
	}

	@Test
	void testPolynomString() {//String Construction 
		second = new Polynom("-5*x^4+4*x^3+3*x^2");//create polynom same to general
		assertTrue(general.equals(second));
	}

	@Test
	void testToString() {
		String str = "" + general;
		assertEquals(str, general.toString());
	}

	@Test
	void testGetSize1() { //Get size of polynom
		second = (Polynom) general.copy();
		Iterator<Monom> it = second.iterator();//make iterator
		int size = 0; //count size
		while(it.hasNext()) {
			size++;
			it.next();
		}
		assertEquals(size, general.getSize());
	}
	
	@Test
	void testGetSize2() {//Get size of empty polynom
		second = new Polynom(); //create polynom
		Iterator<Monom> it = second.iterator();//make iterator
		int size = 0; //count size
		while(it.hasNext()) {
			size++;
			it.next();
		}
		assertEquals(size, second.getSize());
	}

	@Test
	void testF() {
		double result = 0; // sum of f(x)
		x = 3.764; //value to x
		second = (Polynom) general.copy();
		Iterator<Monom> it = second.iterator(); // male iterator
		while(it.hasNext()) {
			result += it.next().f(x);//count of all monoms m.f(x)
		}
		assertEquals(result, general.f(x));
	}

	@Test
	void testAddPolynom_able() {
		second = new Polynom();//Create new Polynom
		second.add(general);//Add polynom to polynom
		assertTrue(second.equals(general));//Check that them same
	}

	@Test
	void testAddMonom() {
		second = new Polynom();//Create new Polynom
		second.add(m1);//Add him same monoms that general include
		second.add(m2);
		second.add(m3);
		assertTrue(general.equals(second));//Check that them equals
	}

	@Test
	void testSubstract() {
		second = new Polynom();//Create new Polynom
		second.add(m1);// Add him some monoms
		second.add(m2);
		second.substract(second);//Polynom subtract same polynom equal 0
		general.substract(general);
		assertTrue(second.equals(general));// check them answers same
	}

	@Test
	void testMultiplyMonom1() {//the correct input
		boolean flag;
		try {
			second = new Polynom();// New Polynom
			m4 = new Monom(m1);//Create new Monom
			m1.multiply(m4);//Multiply our Monoms by new monom
			m2.multiply(m4);
			m3.multiply(m4);
			second.add(m1);//Add all monoms after multiply to new Polynom
			second.add(m2);
			second.add(m3);
			general.multiply(m4);//Multiply the general Polynom by same monom for check them
			flag = general.equals(second);// we must get true if we get that them not equals so we have problem in function
		}catch(RuntimeException e) {
			flag = false;
		}
		assertTrue(flag);
	}
	
	@Test
	void testMultiplyMonom2() {//incorrect input we try to catch the exception
		boolean flag;
		try {
			second = new Polynom();// New Polynom
			m4 = new Monom(m1);//Create new Monom
			m1.multiply(m4);//Multiply our Monoms by new monom
			m2.multiply(m4);
			m3.multiply(m4);
			second.add(m1);//Add all monoms after multiply to new Polynom
			second.add(m2);
			second.add(m3);
			m4 = null;
			general.multiply(m4);//Multiply the general Polynom by null to catch exception
			flag = true; //if we placed in this row so we don't got the exception in function
		}catch(RuntimeException e) {
			flag = false;// we catch exception because prohibited multiply by null
		}
		assertFalse(flag);
	}

	@Test
	void testMultiplyPolynom_able1() {//Two correct others polynoms 
		boolean flag;
		try {
			second = new Polynom(general);//Create new polynom
			m4 = m1.derivative();//Create new differents monoms
			m5 = m2.derivative();
			m6 = m3.derivative();
			second.add(m4);//Add them to polynom
			second.add(m5);
			second.add(m6);
			Polynom temp = new Polynom(second);//Create temp polynom (temp = second)
			second.multiply(general);//second multiply by general
			general.multiply(temp);//general multiply by temp
			flag = second.equals(general);//check if them equals	
		}catch(RuntimeException e) {
			flag = false;// if we got exception function don'r work right
		}
		assertTrue(flag);
	}
	@Test
	void testMultiplyPolynom_able2() {//Second polynom is null we try to catch exception
		boolean flag;
		try {
			second = new Polynom(general);//Create new polynom
			Polynom temp = null;;//Create temp polynom (temp = second)
			second.multiply(general);//second multiply by general
			general.multiply(temp);//general multiply by temp
			flag = false;//if flag was change to false so we don't got the exception in function
		}catch(RuntimeException e) {
			flag = true;//we got the exception, it's good
		}
		assertTrue(flag);
	}

	@Test
	void testIsZero1() {
		second = new Polynom(); //Create new empty polynom
		assertTrue(second.isZero());//Check is zero
	}
	
	@Test
	void testIsZero2() {
		assertFalse(general.isZero());//Check that general not zero (assertFalse)
	}

	@Test
	void testCopy() {
		second = (Polynom) general.copy();
		assertTrue(second.equals(general));
	}

	@Test
	void testDerivative() {
		m4 = m1.derivative();//create new monoms by derivative him 
		m5 = m2.derivative();
		m6 = m3.derivative();
		second = new Polynom();
		second.add(m4);// add all derivatives monoms
		second.add(m5);
		second.add(m6);
		assertTrue(second.equals(general.derivative()));//check the polynom wich we create by ourself with polynom that we derivative by method
	}

	@Test
	void testEqualsPolynom_able1() {//check two empty polynoms
		second = new Polynom(); //create empty polynom 
		general.substract(general);//general - general = empty
		assertTrue(second.equals(general));
	}
	
	@Test
	void testEqualsPolynom_able2() {//check two others polynoms
		second = new Polynom();//create polynom
		second.add(m1);//add two monoms
		second.add(m2);
		assertFalse(second.equals(general));//them not equals so we got false
	}
	
	@Test
	void testEqualsPolynom_able3() {//check two same polynoms
		second = new Polynom();//create polynom
		second.add(m3);//add same monoms like in general polynom
		second.add(m2);
		second.add(m1);
		assertTrue(general.equals(second));//get true
	}

	@Test
	void testArea() {
		//area of -5x^4+4x^3+3x^2 between 0 and 1.272 equal to 1.34601....
		boolean flag = false;
		if(general.area(0, 1.272, 0.001) > 1.33 && general.area(0, 1.272, 0.001) < 1.35) {
			flag = true;
		}
		assertTrue(flag);
	}
	
	@Test
	void testRoot() {
		//root of -5*x^4+4*x^3+3*x^2 between 0.5 to 1.5 it's 1.272
		boolean flag = false;
		if(general.root(0.5, 1.5, 0.001) > 1.271 && general.root(0.5, 1.5, 0.001) < 1.273) {
			flag = true;
		}
		assertTrue(flag);
	}
	
	@Test
	void testIterator1() {
		int index = 0;
		Iterator<Monom> temp = general.iterator();
		while(temp.hasNext()) { //run to polynom and count him monoms
			temp.next();
			index++;
		}
		assertEquals(index, general.getSize());
	}
	
	@Test
	void testIterator2() {
		int index = general.getSize();//index equal to size of polynom
		Iterator<Monom> temp = general.iterator();
		while(temp.hasNext()) { //run to polynom and count him monoms
			temp.next();
			temp.remove(); // remove monoms
			index--;
		}
		assertEquals(index, general.getSize());// check 0 equal to general.size 
	}

}
