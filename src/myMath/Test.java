package myMath;
/**
 * In this class we only check all functions that we create in Polynom class and Monom class. 
 * 
 * @author Max Marmer
 *
 */
public class Test {
	
	public Test() {
		
		Monom m1 = new Monom(5, 4);
		Monom m2 = new Monom(2, 3);
		Monom m3 = new Monom(-3, 1);
		Monom m4 = new Monom(-7, 2);
		Monom m5 = new Monom(6, 6);
		Monom m6 = new Monom(4, 3);
			
		
		Polynom p1 = new Polynom(); // create polynom p1
		p1.add(m1);
		p1.add(m2);
		p1.add(m3);
		p1.add(m4);
		p1.add(m5);
		p1.add(m6);
		
		
		String str = "6*x^8+2*x^3-1*x^2+3*x^4-5*x^7+4*x^1-4*x^2";
		
		
		Polynom p2 = new Polynom(p1); // create polynom p2
		Polynom p3 = new Polynom(str); // create polynom p3

		
		//Print p1, p2, p3
		System.out.println("Polynom1 was empty construction: " + p1.toString());
		System.out.println("Polynom2 copy construction: " + p2.toString());
		System.out.println("Polynom3 String construction: " + p3.toString());
		
		System.out.println("Polynom1 = Polynom2? " + p1.equals(p2));
		
		System.out.println("Polynom1 = Polynom3? " + p1.equals(p3));
		
		p1.add(p2);
		System.out.println("Polynom1 + Polynom2 =  " + p1);
		
		p2.substract(p3);
		System.out.println("Polynom2 - Polynom3 = " + p2);
		
		p1.multiply(p2);
		System.out.println("Polynom1 * Polynom2 = " + p1);
		
		System.out.println("Polynom p1 derivative: " + p1.derivative());
		
		

		
		String str2 = "-3*x^1+2*x^1+1*x^0";
		Polynom p4 = new Polynom(str2); // create polynom by string construction
		
		System.out.println("Polynom4 String construction: " + p4);

		
		System.out.println("The root of p4 is: " + p4.root(0.3, 1.5, 0.01)); // find root of temp2

		System.out.println("The area of " + str + " : " + p3.area(2, 5, 0.00001));// find area of temp1

	}

}
