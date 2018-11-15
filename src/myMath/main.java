package myMath;


import java.awt.Color;
import java.awt.Font;



public class main {



	public static void main(String[] args) {
		//Test allTest = new Test(); // all test placed in other class
		
		
		Monom m1= new Monom(0.2, 4);
		Monom m2 = new Monom(-1.5, 3);
		Monom m3 = new Monom(3, 2);
		Monom m4 = new Monom(-1, 1);
		Monom m5 = new Monom(-5, 0);

		Polynom p1 = new Polynom();
		p1.add(m1);
		p1.add(m2);
		p1.add(m3);
		p1.add(m4);
		p1.add(m5);
		
		System.out.println("Polynom: " + p1);
		System.out.println("Area: " + p1.areaBelowAxisX(-2, 6, 0.01));
		p1.PolynomGraph(-2, 6);


	}

}
