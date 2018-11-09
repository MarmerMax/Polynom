package myMath;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
		general = new Polynom("-5*x^3+7*x^0+3*x^2-2*x^1+1*x^4");
		m1 = new Monom(5, 4);
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
		assertEquals(true, second.equals(general));
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
		assertEquals(true, flag);
	}

//	@Test
//	void testPolynomString() {//String Construction 
//		
//	}

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

//	@Test
//	void testAddPolynom_able() {
//		second = new Polynom();
//		second.add(m1);
//		second.add(m2);
//		second.add(m3);
//		
//	}

//	@Test
//	void testAddMonom() {
//		second = new Polynom(general);
//		general.add(m1);
//		if(second.getSize() == 0 && m1.is_exist()) {
//
//		}
//		
//		assertEquals(true, general.equals(second));
//	}

//	@Test
//	void testSubstract() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testMultiplyMonom() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testMultiplyPolynom_able() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testIsZero() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testCopy() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testDerivative() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testEqualsPolynom_able() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testArea() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testRoot() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testIterator() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testGetMyPolynom() {
//		fail("Not yet implemented");
//	}

}
